package com.launchAggregator.aggregator.model

data class Core(
        val serial: String = "",
        val flightNo: Int = 0,
        val block: Int = 0,
        val gridFin: Boolean = true,
        val legs: Boolean = true
)