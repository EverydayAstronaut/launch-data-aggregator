package com.launchAggregator.aggregator.model.spacex

data class SpacexCore(
        val core_serial: String? = null,
        val flight: Int = 1,
        val block: Int = 5,
        val gridfins: Boolean = true,
        val legs: Boolean = true,
        val landing_intent: Boolean = true,
        val landing_type: String = "",
        val landing_vehicle: String = "",
        val land_success: Boolean = true
)