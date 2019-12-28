package com.launchAggregator.aggregator.model.spacex

data class Fairings(
        val reused: Boolean = false,
        val recovery_attempt: Boolean = false,
        val recovered: Boolean = false,
        val ship: String? = null
)