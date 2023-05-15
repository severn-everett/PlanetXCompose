package com.severett.planetxcompose.jvm

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.severett.planetxcompose.jvm.ui.components.AppContent
import com.severett.planetxcompose.jvm.ui.theme.ComposeTheme

fun main() = application {
    val icon = painterResource("app/apium_dark.jpg")

    Window(
        icon = icon,
        title = "Planet X Demo",
        onCloseRequest = ::exitApplication
    ) {
        ComposeTheme {
            AppContent()
        }
    }
}
