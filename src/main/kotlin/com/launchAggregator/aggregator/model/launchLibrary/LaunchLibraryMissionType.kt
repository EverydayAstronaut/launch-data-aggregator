package com.launchAggregator.aggregator.model.launchLibrary

data class LaunchLibraryMissionType(
        val id: Int = 0,
        val name: String? = null
)

data class LaunchLibraryMissionTypeDataWrapper(
        val types: List<LaunchLibraryMissionType> = listOf()
)