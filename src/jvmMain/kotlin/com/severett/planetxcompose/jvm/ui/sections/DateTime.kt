package com.severett.planetxcompose.jvm.ui.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.severett.planetxcompose.common.model.generateCurrentTime
import com.severett.planetxcompose.common.model.generateDateDifference
import com.severett.planetxcompose.common.model.generateDatesElapsed
import com.severett.planetxcompose.jvm.ui.components.SectionLabel
import com.severett.planetxcompose.jvm.ui.theme.Beige
import com.severett.planetxcompose.jvm.ui.theme.DarkNavy
import com.severett.planetxcompose.jvm.ui.theme.lexendFamily

private val sectionPadding = 4.dp
private val dateDistancePadding = 4.dp

@Composable
fun DateTime() {
    var dateDistance by rememberSaveable { mutableStateOf(45) }
    var selectedTimeZone by rememberSaveable { mutableStateOf("") }
    var currentTime by rememberSaveable(selectedTimeZone) { mutableStateOf(generateCurrentTime(selectedTimeZone)) }
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column {
        // Instant Demo
        SectionLabel(
            text = "Instant Demo",
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = sectionPadding),
            text = generateDatesElapsed(),
            fontSize = 20.sp
        )
        // LocalDate Demo
        SectionLabel(
            text = "LocalDate Demo",
            textAlign = TextAlign.Center,
        )
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = sectionPadding)
        ) {
            IconButton(
                modifier = Modifier.padding(horizontal = dateDistancePadding),
                iconPath = "icon/ic_plus_black_24dp.xml",
            ) { dateDistance += 1 }
            IconButton(
                modifier = Modifier.padding(horizontal = dateDistancePadding),
                iconPath = "icon/ic_minus_black_24dp.xml",
            ) { dateDistance -= 1 }
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = generateDateDifference(dateDistance),
                fontSize = 20.sp
            )
        }
        // LocalDateTime Demo
        SectionLabel(
            text = "LocalDateTime Demo",
            textAlign = TextAlign.Center,
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically).padding(end = 4.dp),
                text = "Select Time Zone:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Box(modifier = Modifier.padding(start = 4.dp)) {
                Surface(color = Color.White, onClick = { expanded = true }) {
                    Row {
                        Text(
                            modifier = Modifier.width(165.dp),
                            text = selectedTimeZone,
                            fontSize = 18.sp,
                            color = DarkNavy,
                            textAlign = TextAlign.Right
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = DarkNavy
                        )
                    }
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    listOf(
                        "",
                        "Africa/Cairo",
                        "America/New_York",
                        "Europe/London",
                        "Asia/Kolkata",
                        "Asia/Tokyo"
                    ).forEach { timeZone ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = timeZone,
                                    fontFamily = lexendFamily,
                                    fontSize = 18.sp
                                )
                            },
                            onClick = {
                                selectedTimeZone = timeZone
                                currentTime = generateCurrentTime(selectedTimeZone)
                                expanded = false
                            },
                            trailingIcon = {
                                if (selectedTimeZone == timeZone) Icon(
                                    Icons.Default.Check,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = sectionPadding),
            text = currentTime,
            fontSize = 20.sp
        )
    }
}

@Composable
private fun IconButton(
    modifier: Modifier = Modifier,
    iconPath: String,
    onClick: () -> Unit,
) {
    FilledTonalButton(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(containerColor = DarkNavy, contentColor = Beige),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(iconPath),
            contentDescription = iconPath
        )
    }
}
