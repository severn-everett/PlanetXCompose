package com.severett.planetxcompose.js.ui.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.severett.planetxcompose.common.model.lockDemo
import com.severett.planetxcompose.common.model.runRace
import com.severett.planetxcompose.js.ui.components.AppButton
import com.severett.planetxcompose.js.ui.components.ContentPane
import com.severett.planetxcompose.js.ui.components.SectionLabel
import com.severett.planetxcompose.js.ui.theme.centerText
import com.severett.planetxcompose.js.ui.theme.gridLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

private val displayTopMargin = 20.px
private val resultsTopMargin = 24.px

@Suppress("OPT_IN_USAGE")
@Composable
fun AtomicFU() {
    var displayRaceResults by remember { mutableStateOf(false) }
    var safeValue by remember { mutableStateOf(0) }
    var unsafeValue by remember { mutableStateOf(0) }
    var displayLockDemo by remember { mutableStateOf(false) }
    var lockDemoValue by remember { mutableStateOf(0) }
    ContentPane {
        SectionLabel("\"Up And Atom!\" Variable Races")
        Div(attrs = {
            style {
                gridLayout(2)
            }
        }) {
            DisplayColumn {
                AppButton("RUN RACE") {
                    GlobalScope.promise { runRace() }.then { (safeResult, unsafeResult) ->
                        safeValue = safeResult
                        unsafeValue = unsafeResult
                        displayRaceResults = true
                    }
                }
                if (displayRaceResults) {
                    TextBlock("Safe Value: $safeValue", resultsTopMargin)
                    TextBlock("Unsafe Value: $unsafeValue", 18.px)
                }
            }
            DisplayColumn {
                AppButton("LOCK DEMO") {
                    GlobalScope.promise { lockDemo() }.then {
                        lockDemoValue = it
                        displayLockDemo = true
                    }
                }
                if (displayLockDemo) {
                    TextBlock("Locked Value: $lockDemoValue", resultsTopMargin)
                }
            }
        }
    }
}

@Composable
private fun DisplayColumn(content: @Composable () -> Unit) {
    Div(attrs = {
        style {
            gridLayout(1)
            display(DisplayStyle.Inline)
            marginTop(displayTopMargin)
        }
    }) {
        content.invoke()
    }
}

@Composable
private fun TextBlock(text: String, topMargin: CSSNumeric) {
    Div(attrs = {
        style {
            marginTop(topMargin)
            centerText()
            fontSize(18.px)
            fontWeight("bold")
        }
    }) {
        Text(text)
    }
}
