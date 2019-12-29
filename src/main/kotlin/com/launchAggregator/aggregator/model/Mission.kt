package com.launchAggregator.aggregator.model

data class Mission(
        val id: Int = 0,
        val name: String = "",
        val description: String = "",
        var orbit: Orbit = Orbit.UNKNOWN,
        val company: String = ""
)