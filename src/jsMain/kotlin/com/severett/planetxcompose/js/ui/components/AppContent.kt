package com.severett.planetxcompose.js.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.severett.planetxcompose.js.ui.model.navItems

@Composable
fun AppContent() {
    var currentItem by remember { mutableStateOf(0) }
    TopBar(navItems[currentItem].title)
    navItems[currentItem].content.invoke()
    BottomNavigationBar(currentItem) { currentItem = it }
}
