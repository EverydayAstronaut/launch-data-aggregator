package com.launchAggregator.aggregator.model.aggregated

import com.launchAggregator.aggregator.model.spacex.Fairings

data class Reusable(
        var reusable: Boolean = false,
        var landing: List<Landing> = listOf(),
        var fairings: Fairings? = null
)