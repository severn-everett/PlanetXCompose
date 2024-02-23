package com.severett.planetxcompose.js.ui.components

import androidx.compose.runtime.Composable
import com.severett.planetxcompose.js.ui.theme.Beige
import com.severett.planetxcompose.js.ui.theme.DarkNavy
import com.severett.planetxcompose.js.ui.theme.center
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(attrs = {
        onClick { onClick.invoke() }
        classes("w3-ripple")
        style {
            center()
            backgroundColor(Beige)
            color(DarkNavy)
            border(style = LineStyle.None)
            borderRadius(4.px)
            padding(16.px)
            fontWeight("bold")
            fontSize(18.px)
        }
    }) {
        Text(text)
    }
}
