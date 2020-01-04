package com.launchAggregator.aggregator.model


data class LaunchDataPageMinimal(
        val totalCount: Int = 0,
        val launches: List<MinimalLaunchData> = listOf()
)