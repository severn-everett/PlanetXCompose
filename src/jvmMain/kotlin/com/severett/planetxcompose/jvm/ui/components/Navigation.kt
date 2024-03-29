package com.severett.planetxcompose.jvm.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.severett.planetxcompose.jvm.ui.model.navItems
import com.severett.planetxcompose.jvm.ui.theme.lexendFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(currentTitle: String = "") {
    CenterAlignedTopAppBar(
        title = { Text(text = currentTitle, fontFamily = lexendFamily, fontWeight = FontWeight.Bold) }
    )
}

@Composable
fun BottomNavigationBar(currentIndex: Int, onChange: (Int) -> Unit) {
    NavigationBar {
        navItems.forEachIndexed { i, navItem ->
            NavigationBarItem(
                selected = currentIndex == i,
                onClick = { onChange.invoke(i) },
                icon = {
                       Icon(
                           painter = painterResource(navItem.navIcon),
                           contentDescription = "${navItem.title}_NavItem"
                       )
                },
                label = { Text(text = navItem.title, fontFamily = lexendFamily, fontSize = 13.sp) },
                alwaysShowLabel = true,
            )
        }
    }
}
