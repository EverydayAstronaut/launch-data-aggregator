package com.launchAggregator.aggregator.model

import com.launchAggregator.aggregator.model.launchLibrary.Launch


data class LaunchData(
        val flight_number: Int = 0,
        val mission: Mission = Mission(),
        val launch: Launch = Launch(),
        val reuse: Reusable = Reusable()
)