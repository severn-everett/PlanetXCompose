package com.severett.planetxcompose.common.model

import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

fun generateDatesElapsed(): String {
    val beginningOfYear = "2024-01-01T00:00:00.000Z".toInstant()
    val now = Clock.System.now()
    val timeElapsed = now - beginningOfYear
    return "Day ${timeElapsed.inWholeDays - 1} of the year."
}

fun generateDateDifference(dateDistance: Int): String {
    val currentDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
    return "$dateDistance day(s) from now is ${currentDate + DatePeriod(days = dateDistance)}"
}

fun generateCurrentTime(timeZone: String): String {
    return if (timeZone.isNotBlank()) {
        val currentTime = Clock.System
            .now()
            .toLocalDateTime(TimeZone.of(timeZone))
            .toString()
        "The current time in $timeZone is $currentTime."
    } else {
        ""
    }
}
