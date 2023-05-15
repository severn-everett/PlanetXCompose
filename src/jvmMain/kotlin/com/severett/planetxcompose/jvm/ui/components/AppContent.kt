package com.severett.planetxcompose.jvm.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.severett.planetxcompose.jvm.ui.model.navItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    val currentItem = rememberSaveable { mutableStateOf(0) }
    Scaffold(
        topBar = { TopBar(currentTitle = navItems[currentItem.value].title) },
        bottomBar = { BottomNavigationBar(currentItem) },
        content = {
            Box(Modifier.padding(it)) {
                navItems[currentItem.value].content.invoke()
            }
        }
    )
}
