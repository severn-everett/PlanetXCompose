package com.severett.planetxcompose.js.ui.theme

import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.keywords.auto

fun StyleScope.center() {
    display(DisplayStyle.Block)
    property("margin-left", auto.toString())
    property("margin-right", auto.toString())
}

fun StyleScope.centerText() {
    display(DisplayStyle.Flex)
    justifyContent(JustifyContent.Center)
}

fun StyleScope.gridLayout(columns: Int) {
    display(DisplayStyle.Grid)
    gridTemplateColumns((0 until columns).joinToString(" ") { auto.toString() })
}
