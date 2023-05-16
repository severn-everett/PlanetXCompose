package com.severett.planetxcompose.jvm.ui.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.severett.planetxcompose.jvm.ui.components.AppButton
import com.severett.planetxcompose.jvm.ui.components.SectionLabel
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.locks.reentrantLock
import kotlinx.atomicfu.locks.withLock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private const val LIMIT = 10_000
private val buttonWidth = 120.dp
private val resultsTopMargin = 32.dp
private val buttonFontSize = 18.sp

@Composable
fun AtomicFU() {
    var displayRaceResults by rememberSaveable { mutableStateOf(false) }
    var safeValue by rememberSaveable { mutableStateOf(0) }
    var unsafeValue by rememberSaveable { mutableStateOf(0) }
    var displayLockDemo by rememberSaveable { mutableStateOf(false) }
    var lockDemoValue by rememberSaveable { mutableStateOf(0) }
    Column {
        SectionLabel(
            modifier = Modifier,
            text = "\"Up And Atom!\" Variable Races",
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.offset(y = 72.dp)
        ) {
            // Atomic Race Section
            DisplayColumn {
                AppButton(
                    modifier = Modifier.width(buttonWidth),
                    onClick = {
                        val (safeResult, unsafeResult) = runRace()
                        safeValue = safeResult
                        unsafeValue = unsafeResult
                        displayRaceResults = true
                    },
                    text = "RUN RACE",
                    fontSize = buttonFontSize
                )
                if (displayRaceResults) {
                    Column {
                        Text(
                            modifier = Modifier.padding(top = resultsTopMargin),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            text = "    Safe Value: $safeValue"
                        )
                        Text(
                            modifier = Modifier.padding(top = 18.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Unsafe Value: $unsafeValue"
                        )
                    }
                }
            }
            // Lock Demo Section
            DisplayColumn {
                AppButton(
                    modifier = Modifier.width(buttonWidth),
                    onClick = {
                        lockDemoValue = lockDemo()
                        displayLockDemo = true
                    },
                    text = "LOCK DEMO",
                    fontSize = buttonFontSize
                )
                if (displayLockDemo) {
                    Text(
                        modifier = Modifier.padding(top = resultsTopMargin),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Locked Value: $lockDemoValue"
                    )
                }
            }
        }
    }
}

@Composable
private inline fun RowScope.DisplayColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}

private fun runRace(): Pair<Int, Int> {
    val safeCounter = atomic(0)
    var unsafeCounter = 0
    runBlocking {
        (0 until LIMIT).map {
            CoroutineScope(Dispatchers.Default).launch {
                safeCounter += 1
                unsafeCounter += 1
            }
        }.forEach { it.join() }
    }
    return safeCounter.value to unsafeCounter
}

private fun lockDemo(): Int {
    var unsafeValue = 0
    val lock = reentrantLock()
    runBlocking {
        (0 until LIMIT).map {
            CoroutineScope(Dispatchers.Default).launch {
                lock.withLock { unsafeValue += 1 }
            }
        }.forEach { it.join() }
    }
    return unsafeValue
}
