package com.severett.planetxcompose.jvm.ui.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.severett.planetxcompose.common.model.runSerde
import com.severett.planetxcompose.jvm.ui.components.AppButton
import com.severett.planetxcompose.jvm.ui.components.InputField
import com.severett.planetxcompose.jvm.ui.components.SectionLabel

private val inputBottomMargin = 4.dp
private val displayTopMargin = 6.dp
private val displayStrSize = 18.sp
private val buttonFontSize = 20.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Serializable() {
    var fizzText by rememberSaveable { mutableStateOf("") }
    var bazzText by rememberSaveable { mutableStateOf("") }
    var countText by rememberSaveable { mutableStateOf("") }
    var isNormalSerde by rememberSaveable { mutableStateOf(true) }
    var displaySerdeLabels by rememberSaveable { mutableStateOf(false) }
    var serializedDisplay by rememberSaveable { mutableStateOf("") }
    var deserializedDisplay by rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        SectionLabel(text = "Enter Fizz:", padding = 26.dp)
        InputField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textValue = fizzText,
            placeholder = "Fizz Value",
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onGo = { keyboardController?.hide() }),
            onValueChange = { fizzText = it }
        )
        SectionLabel(
            modifier = Modifier.padding(top = inputBottomMargin),
            text = "Enter Bazz (Comma-Separated):",
            padding = 26.dp,
        )
        InputField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textValue = bazzText,
            placeholder = "Bazz Value",
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onGo = { keyboardController?.hide() }),
            onValueChange = { bazzText = it }
        )
        SectionLabel(
            modifier = Modifier.padding(top = inputBottomMargin),
            text = "Enter Count:",
            padding = 26.dp,
        )
        InputField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textValue = countText,
            placeholder = "0",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onGo = { keyboardController?.hide() }),
            onValueChange = { countText = it }
        )
        Row(
            modifier = Modifier
                .selectableGroup()
                .align(Alignment.CenterHorizontally)
                .padding(top = inputBottomMargin)
        ) {
            // "Normal" serde radio button
            RadioButton(selected = isNormalSerde, onClick = { isNormalSerde = true })
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "Normal",
                fontSize = 20.sp,
            )
            // "Third-Party" serde radio button
            RadioButton(selected = !isNormalSerde, onClick = { isNormalSerde = false })
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "Third-Party",
                fontSize = 20.sp,
            )
        }
        AppButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                keyboardController?.hide()
                val (serializedStr, deserializedStr) = runSerde(
                    isNormalSerde,
                    fizzText,
                    bazzText,
                    countText
                )
                serializedDisplay = serializedStr
                deserializedDisplay = deserializedStr
                displaySerdeLabels = true
            },
            text = "RUN SERDE",
            fontSize = buttonFontSize
        )
        if (displaySerdeLabels) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = displayTopMargin),
                text = "Serialized Foo",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = displayTopMargin),
                text = serializedDisplay,
                fontSize = displayStrSize
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = displayTopMargin),
                text = "Deserialized Foo",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = displayTopMargin),
                text = deserializedDisplay,
                fontSize = displayStrSize
            )
        }
    }
}
