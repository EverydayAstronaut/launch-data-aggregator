package com.launchAggregator.aggregator.model.spacex

data class SpacexFairings(
        val reused: Boolean = false,
        val recovery_attempt: Boolean = false,
        val recovered: Boolean = false,
        val ship: String = ""
)