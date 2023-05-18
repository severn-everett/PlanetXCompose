package com.severett.planetxcompose.jvm

import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.severett.planetxcompose.jvm.ui.components.AppContent
import com.severett.planetxcompose.jvm.ui.theme.ComposeTheme

fun main() = application {
    val icon = painterResource("app/apium_dark.jpg")

    Window(
        icon = icon,
        title = "Planet X Demo",
        state = rememberWindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(900.dp, 700.dp)
        ),
        onCloseRequest = ::exitApplication
    ) {
        ComposeTheme { AppContent() }
    }
}
