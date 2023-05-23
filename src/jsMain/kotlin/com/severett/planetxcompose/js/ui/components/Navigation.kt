package com.severett.planetxcompose.js.ui.components

import androidx.compose.runtime.Composable
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
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

private val navItemSize = 60.px
private val iconSize = 40.px
private val selectedIconSize = 45.px

@Composable
fun TopBar(currentTitle: String = "") {
    Div(attrs = { style { backgroundColor(ApiumBlack) } }) {
        H2(attrs = {
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
    Div(attrs = {
        style {
            overflow("Hidden")
            position(Position.Fixed)
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
                    style {
                        if (currentIndex == i) {
                            padding(10.px, 10.px, 15.px, 10.px)
                        } else {
                            padding(10.px)
                        }
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
                            if (currentIndex == i) {
                                height(selectedIconSize)
                                width(selectedIconSize)
                            } else {
                                height(iconSize)
                                width(iconSize)
                            }
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
                        if (currentIndex == i) {
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
