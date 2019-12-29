package com.launchAggregator.aggregator.model.spacex

import com.launchAggregator.aggregator.model.Orbit

data class SpacexPayload(
        val payload_id: String = "",
        val payload_mass_kg: Int = 0,
        val orbit: Orbit = Orbit.UNKNOWN,
        val customers: List<String> = listOf(),
        val payload_type: String = "",
        val manufacturer: String = ""
)