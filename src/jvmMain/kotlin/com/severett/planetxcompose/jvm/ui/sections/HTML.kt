package com.severett.planetxcompose.jvm.ui.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.severett.planetxcompose.common.sections.generateHTML
import com.severett.planetxcompose.jvm.ui.components.AppButton
import com.severett.planetxcompose.jvm.ui.components.InputField
import com.severett.planetxcompose.jvm.ui.components.SectionLabel
import com.severett.planetxcompose.jvm.ui.theme.DarkNavy

private val styleLabelSize = 18.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HTML() {
    var nameInput by rememberSaveable { mutableStateOf("") }
    var isBoldChecked by rememberSaveable { mutableStateOf(false) }
    var isStrikethroughChecked by rememberSaveable { mutableStateOf(false) }
    var isUnderlinedChecked by rememberSaveable { mutableStateOf(false) }
    var generatedHtmlStr by rememberSaveable { mutableStateOf("") }

    val helloStr = "Hello, $nameInput!"

    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        SectionLabel(
            text = "Input",
            textAlign = TextAlign.Center,
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            // Name input section
            Column {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Name:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                InputField(
                    textValue = nameInput,
                    placeholder = "Enter Name",
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onGo = { keyboardController?.hide() }),
                    width = 164.dp,
                    onValueChange = { nameInput = it }
                )
            }
            // Style modification section
            Column {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Styling:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                // Bold Checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    XCheckbox(
                        checked = isBoldChecked,
                        onCheckedChange = { isBoldChecked = !isBoldChecked },
                    )
                    Text(
                        text = "Bold",
                        fontSize = styleLabelSize,
                        fontWeight = FontWeight.Bold,
                    )
                }
                // Strikethrough Checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    XCheckbox(
                        checked = isStrikethroughChecked,
                        onCheckedChange = { isStrikethroughChecked = !isStrikethroughChecked },
                    )
                    Text(
                        text = "Strikethrough",
                        fontSize = styleLabelSize,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
                // Underlined Checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    XCheckbox(
                        checked = isUnderlinedChecked,
                        onCheckedChange = { isUnderlinedChecked = !isUnderlinedChecked },
                    )
                    Text(
                        text = "Underlined",
                        fontSize = styleLabelSize,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
        // HTML Generation
        AppButton(
            modifier = Modifier.height(60.dp).width(180.dp).align(Alignment.CenterHorizontally),
            onClick = {
                keyboardController?.hide()
                generatedHtmlStr = generateHTML(
                    helloStr = helloStr,
                    isBold = isBoldChecked,
                    isUnderlined = isUnderlinedChecked,
                    isStrikethrough = isStrikethroughChecked,
                )
            },
            text = "GENERATE",
            fontSize = 20.sp
        )
        SectionLabel(
            modifier = Modifier.padding(top = 10.dp),
            text = "Generated HTML",
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 4.dp, top = 10.dp, end = 4.dp),
            text = generatedHtmlStr,
            fontSize = 20.sp
        )
    }
}

@Composable
private fun XCheckbox(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(checkedColor = DarkNavy, uncheckedColor = DarkNavy),
    )
}
