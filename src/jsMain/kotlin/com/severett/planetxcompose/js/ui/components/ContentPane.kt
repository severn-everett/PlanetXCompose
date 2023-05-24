package com.severett.planetxcompose.js.ui.components

import androidx.compose.runtime.Composable
import com.severett.planetxcompose.js.ui.theme.gridLayout
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
fun ContentPane(content: ContentBuilder<HTMLDivElement>) {
    Div(
        attrs = {
            style {
                gridLayout(1)
                margin(4.em)
            }
        },
        content = content
    )
}
