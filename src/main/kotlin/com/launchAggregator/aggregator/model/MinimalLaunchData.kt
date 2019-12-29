package com.launchAggregator.aggregator.model

import java.time.LocalDateTime

data class MinimalLaunchData(
        val id: Int = 0,
        val name: String = "",
        val orbit: List<Orbit> = listOf(Orbit.UNKNOWN),
        val net: LocalDateTime = LocalDateTime.MIN,
        val agency: Agency = Agency()
)