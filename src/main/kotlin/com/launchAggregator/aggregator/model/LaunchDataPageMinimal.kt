package com.launchAggregator.aggregator.model

import mu.KotlinLogging

private val log = KotlinLogging.logger {}
private const val warnSize = 1000

data class LaunchDataPageMinimal(val totalCount: Int = 0, val launches: List<MinimalLaunchData> = listOf()) {
    init {
        if (launches.size > warnSize) log.warn { "The size of the page: ${launches.size} is exceeding warn size: $warnSize (total: $totalCount)"  }
    }
}
