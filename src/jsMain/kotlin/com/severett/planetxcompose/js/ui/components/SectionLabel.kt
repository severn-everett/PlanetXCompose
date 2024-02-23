package com.severett.planetxcompose.js.ui.components

import androidx.compose.runtime.Composable
import com.severett.planetxcompose.js.ui.theme.DarkNavy
import com.severett.planetxcompose.js.ui.theme.White
import com.severett.planetxcompose.js.ui.theme.centerText
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun SectionLabel(text: String = "") {
    Div(attrs = {
        style {
            backgroundColor(DarkNavy)
            color(White)
            centerText()
            fontSize(22.px)
            fontWeight("bold")
            padding(8.px, 0.px)
        }
    }) {
        Text(value = text)
    }
}
