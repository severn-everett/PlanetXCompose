package com.severett.planetxcompose.js.ui.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.severett.planetxcompose.common.model.generateCurrentTime
import com.severett.planetxcompose.common.model.generateDateDifference
import com.severett.planetxcompose.common.model.generateDatesElapsed
import com.severett.planetxcompose.js.ui.components.ContentPane
import com.severett.planetxcompose.js.ui.components.SectionLabel
import com.severett.planetxcompose.js.ui.theme.DarkNavy
import com.severett.planetxcompose.js.ui.theme.beigeFilter
import com.severett.planetxcompose.js.ui.theme.center
import com.severett.planetxcompose.js.ui.theme.centerText
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Select
import org.jetbrains.compose.web.dom.Text

private val sectionPadding = 8.px
private val iconSize = 36.px
private val iconPadding = 4.px
private val localDatePadding = 4.px
private val timeZonePadding = 8.px

@Composable
fun DateTime() {
    var dateDistance by remember { mutableStateOf(45) }
    var selectedTimeZone by remember { mutableStateOf("") }
    var currentTime by remember(selectedTimeZone) { mutableStateOf(generateCurrentTime(selectedTimeZone)) }
    ContentPane {
        // Instant Demo
        SectionLabel("Instant Demo")
        Div(attrs = {
            style {
                centerText()
                fontSize(20.px)
                padding(sectionPadding, 0.px)
            }
        }) {
            Text(generateDatesElapsed())
        }
        // LocalDate Demo
        SectionLabel("LocalDate Demo")
        Div(attrs = {
            style {
                padding(sectionPadding, 0.px)
                fontSize(20.px)
                centerText()
            }
        }) {
            Div(attrs = {
                style {
                    centerText()
                    paddingRight(localDatePadding)
                }
            }) {
                Div(attrs = { style { paddingRight(iconPadding) } }) {
                    IconButton("icons/ic_plus_black_24dp.svg") { dateDistance += 1 }
                }
                Div(attrs = { style { paddingLeft(iconPadding) } }) {
                    IconButton("icons/ic_minus_black_24dp.svg") { dateDistance -= 1 }
                }
            }
            Div(attrs = {
                style {
                    display(DisplayStyle.Flex)
                    alignItems("center")
                    paddingLeft(localDatePadding)
                }
            }) {
                Text(generateDateDifference(dateDistance))
            }
        }
        // LocalDateTime Demo
        SectionLabel("LocalDateTime Demo")
        Div(attrs = {
            style {
                paddingTop(sectionPadding)
                centerText()
            }
        }) {
            val timeZoneSelectId = "timeZoneSelect"
            Label(
                forId = timeZoneSelectId,
                attrs = {
                    style {
                        fontSize(20.px)
                        fontWeight("bold")
                        paddingRight(timeZonePadding)
                    }
                }
            ) {
                Text("Select Time Zone:")
            }
            Select(attrs = {
                id(timeZoneSelectId)
                style { paddingLeft(timeZonePadding) }
                onChange {
                    selectedTimeZone = it.value ?: ""
                    println("TEST: $selectedTimeZone")
                    currentTime = generateCurrentTime(selectedTimeZone)
                    println("CURRENT TIME: $currentTime")
                }
            }) {
                listOf(
                    "",
                    "Africa/Cairo",
                    "America/New_York",
                    "Europe/London",
                    "Asia/Kolkata",
                    "Asia/Tokyo"
                ).forEach { timeZone ->
                    Option(value = timeZone) { Text(timeZone) }
                }
            }
        }
        Div(attrs = {
            style {
                fontSize(20.px)
                centerText()
                paddingTop(sectionPadding)
            }
        }) {
            Text(currentTime)
        }
    }
}

@Composable
private fun IconButton(iconPath: String, onClick: () -> Unit) {
    Button(attrs = {
        onClick { onClick.invoke() }
        classes("w3-ripple")
        style {
            center()
            backgroundColor(DarkNavy)
            border(style = LineStyle.None)
            borderRadius(4.px)
            padding(12.px)
        }
    }) {
        Img(
            src = iconPath,
            attrs = {
                style {
                    height(iconSize)
                    width(iconSize)
                    center()
                    beigeFilter()
                }
            }
        )
    }
}
