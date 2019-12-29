package com.launchAggregator.aggregator.model.launchLibrary

data class LaunchLibraryMission(
        val id: Int = 0,
        val name: String = "",
        val description: String = "",
        val type: Int = 0,
        val infoUrl: String = ""
)

data class LaunchLibraryMissionDataWrapper(
        val missions: List<LaunchLibraryMission> = listOf()
)