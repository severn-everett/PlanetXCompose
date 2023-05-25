package com.severett.planetxcompose.js.ui.theme

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.percent

fun StyleScope.center() {
    display(DisplayStyle.Block)
    property("margin-left", auto.toString())
    property("margin-right", auto.toString())
}

fun StyleScope.centerText() {
    display(DisplayStyle.Flex)
    justifyContent(JustifyContent.Center)
}

fun StyleScope.unselectable() {
    property("-webkit-user-select", "none")
    property("-ms-user-select", "none")
    property("user-select", "none")
}

fun StyleScope.gridLayout(columns: Int) {
    display(DisplayStyle.Grid)
    gridTemplateColumns((0 until columns).joinToString(" ") { 1.fr.toString() })
}

@OptIn(ExperimentalComposeWebApi::class)
fun StyleScope.apiumGreenFilter() {
    filter {
        invert(82.percent)
        sepia(44.percent)
        saturate(455.percent)
        hueRotate(72.deg)
        brightness(96.percent)
        contrast(98.percent)
    }
}
