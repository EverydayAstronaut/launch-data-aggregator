package com.launchAggregator.aggregator.model.aggregated

data class Landing(
        val attempt: Boolean = false,
        val landing_type: String? = null,
        val landing_vehicle: String? = null,
        val flights: Int = 0
)