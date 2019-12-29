package com.launchAggregator.aggregator.model.launchLibrary

data class LaunchLibraryAgency (
        val id: Int = 0,
        val name: String = "",
        val abbrev: String = "",
        val type: Int = 0,
        val islsp: Boolean = false,
        val countryCode: String = ""
)