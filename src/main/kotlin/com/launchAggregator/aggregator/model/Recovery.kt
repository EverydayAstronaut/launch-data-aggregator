package com.launchAggregator.aggregator.model

data class Recovery(
        val fairing: RecoveryInfo = RecoveryInfo(),
        val cores: List<RecoveryInfo> = listOf()
)