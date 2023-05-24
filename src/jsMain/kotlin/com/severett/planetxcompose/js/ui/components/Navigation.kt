package com.severett.planetxcompose.js.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.severett.planetxcompose.js.ui.model.navItems
import com.severett.planetxcompose.js.ui.theme.ApiumBlack
import com.severett.planetxcompose.js.ui.theme.ApiumGreen
import com.severett.planetxcompose.js.ui.theme.center
import com.severett.planetxcompose.js.ui.theme.centerText
import com.severett.planetxcompose.js.ui.theme.gridLayout
import com.severett.planetxcompose.js.ui.theme.unselectable
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.overflow
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

private val navItemSize = 60.px
private val navItemSidePadding = 10.px
private val normalIconSize = 40.px
private val selectedIconSize = 42.px
private val highlightedIconSize = 45.px

@Composable
fun TopBar(currentTitle: String = "") {
    Div(attrs = { style { backgroundColor(ApiumBlack) } }) {
        H1(attrs = {
            style {
                centerText()
                color(ApiumGreen)
            }
        }) { Text(currentTitle) }
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun BottomNavigationBar(currentIndex: Int, onChange: (Int) -> Unit) {
    var highlightedIndex by remember { mutableStateOf(-1) }
    Div(attrs = {
        style {
            overflow("Hidden")
            position(Position.Fixed)
            boxSizing("initial")
            bottom(0.px)
            width(100.percent)
            backgroundColor(ApiumBlack)
            gridLayout(navItems.size)
        }
    }) {
        navItems.forEachIndexed { i, navItem ->
            Div(
                attrs = {
                    onClick { onChange.invoke(i) }
                    onMouseEnter { highlightedIndex = i }
                    onMouseLeave { highlightedIndex = -1 }
                    style {
                        val navItemTopPadding = when (i) {
                            highlightedIndex -> 10.px
                            currentIndex -> 13.px
                            else -> 15.px
                        }
                        padding(navItemTopPadding, navItemSidePadding)
                        color(ApiumGreen)
                        height(navItemSize)
                        width(navItemSize)
                        center()
                        gridLayout(1)
                    }
                }
            ) {
                Img(
                    src = navItem.navIcon,
                    attrs = {
                        style {
                            val iconSize = when (i) {
                                highlightedIndex -> highlightedIconSize
                                currentIndex -> selectedIconSize
                                else -> normalIconSize
                            }
                            height(iconSize)
                            width(iconSize)
                            center()
                            filter {
                                invert(82.percent)
                                sepia(44.percent)
                                saturate(455.percent)
                                hueRotate(72.deg)
                                brightness(96.percent)
                                contrast(98.percent)
                            }
                        }
                    }
                )
                Span(attrs = {
                    style {
                        centerText()
                        unselectable()
                        if (currentIndex == i || highlightedIndex == i) {
                            fontWeight("bold")
                        }
                    }
                }) {
                    Text(value = navItem.title)
                }
            }
        }
    }
}
