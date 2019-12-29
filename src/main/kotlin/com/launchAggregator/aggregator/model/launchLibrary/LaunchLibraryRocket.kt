package com.launchAggregator.aggregator.model.launchLibrary

data class LaunchLibraryRocket(
        val id: Int = 0,
        val name: String = "",
        val configuration: String = "",
        val familyname: String = "",
        val imageURL: String = "",
        val imageSizes: List<Int> = listOf()
)