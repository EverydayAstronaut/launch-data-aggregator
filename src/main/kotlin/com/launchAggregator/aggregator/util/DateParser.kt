package com.launchAggregator.aggregator.util

import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class DateParser {
    fun toLocalDateTime(date: String): LocalDateTime {
        val formatter = SimpleDateFormat("MMMM dd, yyyy HH:mm:ss Z")
        formatter.timeZone = TimeZone.getTimeZone("618")
        return formatter.parse(date).toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime() ?: LocalDateTime.MIN
    }

    fun getTimeLeft(ldt1: LocalDateTime, ldt2: LocalDateTime, timeUnit: TimeUnit): Int {
        val minusHours = ldt1.minusHours(ldt2.hour.toLong())
        val minusMinutes = minusHours.minusMinutes(ldt2.minute.toLong())
        val minusSeconds = minusMinutes.minusSeconds(ldt2.second.toLong())
        val ms = minusSeconds.hour * 3600000 + minusSeconds.minute * 60000 + minusSeconds.second * 1000
        return when (timeUnit) {
            TimeUnit.MILLISECONDS -> ms
            TimeUnit.SECONDS -> ms * 1000
            TimeUnit.MINUTES -> ms * 60000
            TimeUnit.HOURS -> ms * 3600000
            else -> ms
        }
    }
}