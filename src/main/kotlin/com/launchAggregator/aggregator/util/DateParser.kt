package com.launchAggregator.aggregator.util

import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Component
class DateParser {
    fun toLocalDateTime(date: String): LocalDateTime {
        val formatter = SimpleDateFormat("MMMM dd, yyyy HH:mm:ss Z")
        formatter.timeZone = TimeZone.getTimeZone("618")
        return formatter.parse(date).toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime() ?: LocalDateTime.MIN
    }
}