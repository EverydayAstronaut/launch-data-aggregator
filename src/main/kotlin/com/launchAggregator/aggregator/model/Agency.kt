package com.launchAggregator.aggregator.model

data class Agency(
        val name: String = "",
        val short: String = "",
        val type: Int = 0,
        val isLsp: Boolean = true,
        val location: String = ""
)