package com.severett.planetxcompose.jvm.ui.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
private val buttonTopMargin = 72.dp
private val buttonSideMargin = 50.dp
private val buttonWidth = 120.dp
private val resultsTopMargin = 32.dp
private val raceSideMargin = 12.dp
private val lockDemoSideMargin = 12.dp
private val buttonFontSize = 18.sp

@Composable
fun AtomicFU(modifier: Modifier = Modifier) {
    var displayRaceResults by rememberSaveable { mutableStateOf(false) }
    var safeValue by rememberSaveable { mutableStateOf(0) }
    var unsafeValue by rememberSaveable { mutableStateOf(0) }
    var displayLockDemo by rememberSaveable { mutableStateOf(false) }
    var lockDemoValue by rememberSaveable { mutableStateOf(0) }
    Column {
        SectionLabel(
            modifier = modifier,
            text = "\"Up And Atom!\" Variable Races",
            textAlign = TextAlign.Center
        )
        Row {
            // Atomic Race Section
            Column {
                AppButton(
                    modifier = modifier.width(buttonWidth),
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
                            modifier = modifier,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Safe Value: $safeValue"
                        )
                        Text(
                            modifier = modifier,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Unsafe Value: $unsafeValue"
                        )
                    }
                }
            }
            // Lock Demo Section
            Column {
                AppButton(
                    modifier = modifier.width(buttonWidth),
                    onClick = {
                        lockDemoValue = lockDemo()
                        displayLockDemo = true
                    },
                    text = "LOCK DEMO",
                    fontSize = buttonFontSize
                )
                if (displayLockDemo) {
                    Text(
                        modifier = modifier,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Locked Value: $lockDemoValue"
                    )
                }
            }
        }
    }
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
