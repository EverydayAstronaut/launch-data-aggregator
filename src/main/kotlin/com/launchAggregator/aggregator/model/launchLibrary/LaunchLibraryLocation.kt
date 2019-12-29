package com.launchAggregator.aggregator.model.launchLibrary

import com.launchAggregator.aggregator.model.Pad

data class LaunchLibraryLocation(
        val id: Int = 0,
        val pads: List<Pad> = listOf(),
        val name: String = "",
        val countryCode: String = ""
)