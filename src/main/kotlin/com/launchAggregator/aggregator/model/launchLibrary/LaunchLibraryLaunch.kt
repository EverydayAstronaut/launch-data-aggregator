package com.launchAggregator.aggregator.model.launchLibrary

import javax.xml.stream.Location

data class LaunchLibraryLaunch(
        val id: Int = 0,
        val name: String = "",
        val windowstart: String = "",
        val status: Int = 0,
        val tbdtime: Int = 0,
        val tbddate: Int = 0,
        val lsp: LaunchLibraryAgency = LaunchLibraryAgency(),
        val rocket: LaunchLibraryRocket = LaunchLibraryRocket(),
        val missions: List<LaunchLibraryMission> = listOf(),
        val location: LaunchLibraryLocation = LaunchLibraryLocation()
)

data class LaunchLibraryLaunchDataWrapper(
        val launches: List<LaunchLibraryLaunch> = listOf()
)