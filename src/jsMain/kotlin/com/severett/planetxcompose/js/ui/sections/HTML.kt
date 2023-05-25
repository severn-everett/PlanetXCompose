package com.severett.planetxcompose.js.ui.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.severett.planetxcompose.common.sections.generateHTML
import com.severett.planetxcompose.js.ui.components.AppButton
import com.severett.planetxcompose.js.ui.components.ContentPane
import com.severett.planetxcompose.js.ui.components.InputField
import com.severett.planetxcompose.js.ui.components.SectionLabel
import com.severett.planetxcompose.js.ui.theme.ApiumGreen
import com.severett.planetxcompose.js.ui.theme.center
import com.severett.planetxcompose.js.ui.theme.centerText
import com.severett.planetxcompose.js.ui.theme.gridLayout
import kotlinx.browser.document
import kotlinx.html.stream.createHTML
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.marginRight
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text

private val checkboxSideMargin = 8.px

@Composable
fun HTML() {
    var nameInput by remember { mutableStateOf("") }
    var isBoldChecked by remember { mutableStateOf(false) }
    var isStrikethroughChecked by remember { mutableStateOf(false) }
    var isUnderlinedChecked by remember { mutableStateOf(false) }
    var generatedHtmlStr by remember { mutableStateOf("") }

    val helloStr = "Hello, $nameInput!"

    ContentPane {
        SectionLabel("Input")
        Div(attrs = {
            style {
                gridLayout(2)
                paddingTop(18.px)
            }
        }) {
            // Name input section
            Div(attrs = {
                style {
                    gridLayout(1)
                }
            }) {
                InputLabel("Name:")
                InputField(InputType.Text, "Enter Name") { nameInput = it }
            }
            // Style modification section
            Div(attrs = {
                style {
                    gridLayout(1)
                    center()
                }
            }) {
                InputLabel("Style:")
                CheckboxInput("isBold", "Bold", { fontWeight("bold") }, isBoldChecked) {
                    isBoldChecked = !isBoldChecked
                }
                CheckboxInput(
                    "isStrikethrough",
                    "Strikethrough",
                    { textDecoration("line-through") },
                    isStrikethroughChecked
                ) {
                    isStrikethroughChecked = !isStrikethroughChecked
                }
                CheckboxInput(
                    "isUnderlined",
                    "Underlined",
                    { fontWeight("underline") },
                    isUnderlinedChecked
                ) {
                    isUnderlinedChecked = !isUnderlinedChecked
                }
            }
        }
        AppButton("GENERATE") {
            generatedHtmlStr = generateHTML(
                helloStr = helloStr,
                isBold = isBoldChecked,
                isUnderlined = isUnderlinedChecked,
                isStrikethrough = isStrikethroughChecked,
            )
        }
        Div(attrs = { style { paddingTop(12.px) } }) {
            SectionLabel("Generated HTML")
            Div(attrs = {
                style {
                    fontSize(20.px)
                    paddingTop(12.px)
                    centerText()
                }
            }) {
                Text(generatedHtmlStr)
            }
        }
    }
}

@Composable
private fun InputLabel(text: String) {
    Div(attrs = {
        style {
            fontSize(20.px)
            fontWeight("bold")
            centerText()
        }
    }) {
        Text(text)
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun CheckboxInput(
    id: String,
    text: String,
    labelStyle: StyleScope.() -> Unit,
    checked: Boolean,
    onClicked: () -> Unit
) {
    Div(attrs = { style { marginLeft(22.px) } }) {
        Input(type = InputType.Checkbox, attrs = {
            checked(checked)
            onClick { onClicked.invoke() }
            style {
                if (checked) property("accent-color", ApiumGreen.toString())
                marginRight(checkboxSideMargin)
                transform { scale(1.5) }
            }
        })
        Label(forId = id, attrs = {
            style {
                fontSize(18.px)
                this.labelStyle()
                marginLeft(checkboxSideMargin)
            }
        }) {
            Text(text)
        }
    }
}
