package com.launchAggregator.aggregator.model.spacex

data class SecondStage(
        val block: Int = 5,
        val payloads: List<Payload> = listOf(Payload())
)