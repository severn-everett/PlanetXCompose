package com.severett.planetxcompose.jvm.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.severett.planetxcompose.jvm.ui.model.navItems

@Composable
fun AppContent() {
    var currentItem by rememberSaveable { mutableStateOf(0) }
    Scaffold(
        topBar = { TopBar(currentTitle = navItems[currentItem].title) },
        bottomBar = { BottomNavigationBar(currentItem) { currentItem = it} },
        content = { Box(Modifier.padding(it)) { navItems[currentItem].content.invoke() } }
    )
}
