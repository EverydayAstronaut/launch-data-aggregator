package com.launchAggregator.aggregator.model

import com.launchAggregator.aggregator.model.spacex.SpacexModel
import mu.KotlinLogging

private val log = KotlinLogging.logger {}
private const val warnSize = 1000

data class LaunchDataPage(val totalCount: Int = 0, val launches: List<SpacexModel> = listOf()) {
    init {
        if (launches.size > warnSize) log.warn { "The size of the page: ${launches.size} is exceeding warn size: $warnSize (total: $totalCount)"  }
    }
}
