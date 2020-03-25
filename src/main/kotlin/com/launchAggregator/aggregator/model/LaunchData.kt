package com.launchAggregator.aggregator.model

import java.time.LocalDateTime

data class LaunchData(
        val id: Int = 0,
        val name: String = "",
        val net: LocalDateTime = LocalDateTime.MIN,
        val netStatus: Int = 0,
        val location: Location = Location(),
        var missions: List<Mission> = listOf(),
        val rocket: Rocket = Rocket(),
        var recovery: Recovery = Recovery(),
        var ttl: Int = -1
)