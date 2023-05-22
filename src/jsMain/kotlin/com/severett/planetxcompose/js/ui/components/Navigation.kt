package com.severett.planetxcompose.js.ui.components

import androidx.compose.runtime.Composable
import com.severett.planetxcompose.js.ui.model.navItems
import com.severett.planetxcompose.js.ui.theme.ApiumBlack
import com.severett.planetxcompose.js.ui.theme.ApiumGreen
import com.severett.planetxcompose.js.ui.theme.center
import com.severett.planetxcompose.js.ui.theme.gridLayout
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.overflow
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

private val navItemSize = 75.px
private val iconSize = 50.px

@Composable
fun TopBar(currentTitle: String = "") {
    Div {
        H3 { Text(currentTitle) }
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
                    onClick {
                        println("TEST: $i")
                        onChange.invoke(i)
                    }
                    style {
                        backgroundColor(ApiumBlack)
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
                        display(DisplayStyle.Flex)
                        justifyContent(JustifyContent.Center)
                    }
                }) {
                    Text(value = navItem.title)
                }
            }
        }
    }
}
