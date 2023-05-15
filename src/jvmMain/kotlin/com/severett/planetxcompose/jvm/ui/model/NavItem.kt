package com.severett.planetxcompose.jvm.ui.model

import androidx.compose.runtime.Composable
import com.severett.planetxcompose.jvm.ui.sections.AtomicFU

data class NavItem(
    val route: String,
    val title: String,
    val navIcon: String,
    val content: @Composable () -> Unit
)

val navItems = listOf(
    NavItem(
        route = "/atomicFU",
        title = "AtomicFU",
        navIcon = "icon/ic_atomicfu_black_24dp.xml",
        content = { AtomicFU() }
    ),
)
