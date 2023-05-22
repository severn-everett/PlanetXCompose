package com.severett.planetxcompose.js

import com.severett.planetxcompose.js.ui.components.AppContent
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        AppContent()
    }
}
