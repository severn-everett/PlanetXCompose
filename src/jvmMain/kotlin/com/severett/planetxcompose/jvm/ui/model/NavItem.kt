package com.severett.planetxcompose.jvm.ui.model

import androidx.compose.runtime.Composable
import com.severett.planetxcompose.jvm.ui.sections.AtomicFU
import com.severett.planetxcompose.jvm.ui.sections.DateTime
import com.severett.planetxcompose.jvm.ui.sections.HTML
import com.severett.planetxcompose.jvm.ui.sections.Serializable

data class NavItem(
    val route: String,
    val title: String,
    val navIcon: String,
    val content: @Composable () -> Unit
)

val navItems = listOf(
    NavItem(
        route = "/serializable",
        title = "Serializable",
        navIcon = "icon/ic_json_black_24dp.xml",
        content = { Serializable() }
    ),
    NavItem(
        route = "/atomicFU",
        title = "AtomicFU",
        navIcon = "icon/ic_atomicfu_black_24dp.xml",
        content = { AtomicFU() }
    ),
    NavItem(
        route = "/html",
        title = "HTML",
        navIcon = "icon/ic_html_black_24dp.xml",
        content = { HTML() }
    ),
    NavItem(
        route = "/datetime",
        title = "DateTime",
        navIcon = "icon/ic_calendar_black_24dp.xml",
        content = { DateTime() }
    ),
)
