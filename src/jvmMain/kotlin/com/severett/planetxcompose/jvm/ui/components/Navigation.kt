package com.severett.planetxcompose.jvm.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.severett.planetxcompose.jvm.ui.model.navItems
import com.severett.planetxcompose.jvm.ui.theme.europaFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(currentTitle: String = "") {
    CenterAlignedTopAppBar(
        title = { Text(text = currentTitle, fontFamily = europaFamily, fontWeight = FontWeight.Bold) }
    )
}

@Composable
fun BottomNavigationBar(currentItem: MutableState<Int>) {
    NavigationBar {
        navItems.forEachIndexed { i, navItem ->
            NavigationBarItem(
                selected = currentItem.value == i,
                onClick = { currentItem.value = i },
                icon = {
                       Icon(
                           painter = painterResource(navItem.navIcon),
                           contentDescription = "${navItem.title}_NavItem"
                       )
                },
                label = {
                    Text(text = navItem.title, fontFamily = europaFamily, fontSize = 13.sp)
                },
                alwaysShowLabel = true,
            )
        }
    }
}
