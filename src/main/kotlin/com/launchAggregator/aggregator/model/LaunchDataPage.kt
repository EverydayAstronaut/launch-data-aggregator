package com.launchAggregator.aggregator.model


data class LaunchDataPage(
        val totalCount: Int = 0,
        val launches: List<LaunchData> = listOf()
)