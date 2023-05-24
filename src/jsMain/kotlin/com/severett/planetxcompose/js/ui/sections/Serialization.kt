package com.severett.planetxcompose.js.ui.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.severett.planetxcompose.common.serde.runSerde
import com.severett.planetxcompose.js.ui.components.AppButton
import com.severett.planetxcompose.js.ui.components.InputField
import com.severett.planetxcompose.js.ui.components.SectionLabel
import com.severett.planetxcompose.js.ui.theme.ApiumGreen
import com.severett.planetxcompose.js.ui.theme.centerText
import com.severett.planetxcompose.js.ui.theme.gridLayout
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.RadioInput
import org.jetbrains.compose.web.dom.Text

private val radioButtonSidePadding = 8.px
private val displayTopPadding = 12.px

@Composable
fun Serialization() {
    var fizzText by remember { mutableStateOf("") }
    var bazzText by remember { mutableStateOf("") }
    var countText by remember { mutableStateOf("") }
    var isNormalSerde by remember { mutableStateOf(true) }
    var displaySerdeLabels by remember { mutableStateOf(false) }
    var serializedDisplay by remember { mutableStateOf("") }
    var deserializedDisplay by remember { mutableStateOf("") }
    Div(attrs = {
        style {
            gridLayout(1)
            margin(4.em)
        }
    }) {
        SectionLabel(text = "Enter Fizz:")
        InputField(type = InputType.Text, placeHolder = "Fizz Value") { fizzText = it }
        SectionLabel(text = "Enter Bazz (Comma-Separated):")
        InputField(type = InputType.Text, placeHolder = "Bazz Value") { bazzText = it }
        SectionLabel(text = "Enter Count:")
        InputField(type = InputType.Number, placeHolder = "0") { countText = it }
        Div(attrs = { style { gridLayout(2) } }) {
            Div(attrs = {
                style {
                    gridLayout(2)
                    property("margin-left", "auto")
                    property("margin-right", "0")
                    paddingRight(radioButtonSidePadding)
                }
            }) {
                RadioButton("isNormalSerde", "Normal", isNormalSerde) { isNormalSerde = true }
            }
            Div(attrs = {
                style {
                    gridLayout(2)
                    property("margin-left", "0")
                    property("margin-right", "auto")
                    paddingLeft(radioButtonSidePadding)
                }
            }) {
                RadioButton("isThirdPartySerde", "Third-Party", !isNormalSerde) { isNormalSerde = false }
            }
        }
        Div(attrs = { style { paddingTop(displayTopPadding) } }) {
            AppButton("RUN SERDE") {
                val (serializedStr, deserializedStr) = runSerde(isNormalSerde, fizzText, bazzText, countText)
                serializedDisplay = serializedStr
                deserializedDisplay = deserializedStr
                displaySerdeLabels = true
            }
        }
        if (displaySerdeLabels) {
            ResultSection("Serialized Foo", serializedDisplay)
            ResultSection("Deserialized Foo", deserializedDisplay)
        }
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun RadioButton(id: String, text: String, checked: Boolean, onClicked: () -> Unit) {
    Label(forId = id, attrs = { style { fontSize(18.px) } }) { Text(text) }
    RadioInput(checked = checked, attrs = {
        id(id)
        onClick { onClicked.invoke() }
        style {
            if (checked) property("accent-color", ApiumGreen.toString())
            transform {
                scale(1.5)
            }
            marginLeft(8.px)
        }
    })
}

@Composable
private fun ResultSection(title: String, content: String) {
    Div(attrs = {
        style {
            paddingTop(displayTopPadding)
            centerText()
            fontSize(20.px)
            fontWeight("bold")
        }
    }
    ) {
        Text(title)
    }
    Div(attrs = {
        style {
            paddingTop(displayTopPadding)
            centerText()
            fontSize(18.px)
        }
    }) {
        Text(content)
    }
}
