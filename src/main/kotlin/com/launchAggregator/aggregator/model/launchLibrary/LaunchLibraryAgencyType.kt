package com.launchAggregator.aggregator.model.launchLibrary

data class LaunchLibraryAgencyType (
        val id: Int = 0,
        val name: String = ""
)

data class LaunchLibraryAgencyTypeDataWrapper(
        val types: List<LaunchLibraryAgencyType> = listOf()
)