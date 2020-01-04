package com.launchAggregator.aggregator.model

data class RecoveryInfo(
        val serial: String = "",
        val attempt: Boolean = false,
        val onShip: Boolean = false,
        val location: List<String> = listOf(),
        val recovered: Boolean = false
)