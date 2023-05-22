package com.severett.planetxcompose.js.ui.model

import androidx.compose.runtime.Composable
import com.severett.planetxcompose.js.ui.sections.AtomicFU
import com.severett.planetxcompose.js.ui.sections.DateTime
import com.severett.planetxcompose.js.ui.sections.HTML
import com.severett.planetxcompose.js.ui.sections.Serialization

data class NavItem(
    val route: String,
    val title: String,
    val navIcon: String,
    val content: @Composable () -> Unit
)

val navItems = listOf(
    NavItem(
        route = "/serialization",
        title = "Serialization",
        navIcon = "icons/ic_json_black_24dp.svg",
        content = { Serialization() }
    ),
    NavItem(
        route = "/atomicfu",
        title = "AtomicFU",
        navIcon = "icons/ic_atomicfu_black_24dp.svg",
        content = { AtomicFU() }
    ),
    NavItem(
        route = "/html",
        title = "HTML",
        navIcon = "icons/ic_html_black_24dp.svg",
        content = { HTML() }
    ),
    NavItem(
        route = "/datetime",
        title = "DateTime",
        navIcon = "icons/ic_calendar_black_24dp.svg",
        content = { DateTime() }
    ),
)
