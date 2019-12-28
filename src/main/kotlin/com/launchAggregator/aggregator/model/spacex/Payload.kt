package com.launchAggregator.aggregator.model.spacex

import com.launchAggregator.aggregator.model.Orbit

data class Payload(
        val payload_id: String? = null,
        val payload_mass_kg: Int = 0,
        val orbit: Orbit = Orbit.UNKNOWN,
        val customers: List<String> = listOf(),
        val payload_type: String? = null,
        val manufacturer: String? = null
)