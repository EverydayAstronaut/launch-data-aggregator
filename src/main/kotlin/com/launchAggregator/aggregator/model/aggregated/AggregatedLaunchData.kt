package com.launchAggregator.aggregator.model.aggregated

import com.launchAggregator.aggregator.model.launchLibrary.Launch
import java.time.LocalDateTime

data class AggregatedLaunchData(
        var id: Int = 0,
        val name: String = "",
        val net: LocalDateTime = LocalDateTime.now(),
        val status: Int = 0,
        val tbdtime: Int = 0,
        val tbddate: Int = 0,
        val mission: Mission = Mission(),
        val launch: Launch = Launch(),
        val reuse: Reusable = Reusable(),
        val rocket: Rocket = Rocket(),
        val socialMedia: String = ""
)