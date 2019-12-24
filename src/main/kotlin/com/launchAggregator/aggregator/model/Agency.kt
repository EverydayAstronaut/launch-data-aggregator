package com.launchAggregator.aggregator.model

data class Agency (
        val id: Int = 0,
        val name: String = "",
        val abbrev: String = "",
        val type: Int = 0,
        val islsp: Boolean = false
)