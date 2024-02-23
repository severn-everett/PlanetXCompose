package com.severett.planetxcompose.jvm.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.severett.planetxcompose.jvm.ui.theme.DarkNavy
import com.severett.planetxcompose.jvm.ui.theme.White

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    textValue: String = "",
    placeholder: String = "",
    width: Dp = 360.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        value = textValue,
        modifier = modifier
            .width(width)
            .height(54.dp),
        placeholder = { Text(text = placeholder) },
        textStyle = TextStyle(fontSize = 18.sp, color = DarkNavy),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            focusedPlaceholderColor = DarkNavy,
            focusedIndicatorColor = DarkNavy,
            unfocusedContainerColor = White,
            unfocusedPlaceholderColor = DarkNavy,
            cursorColor = DarkNavy,
        )
    )
}
