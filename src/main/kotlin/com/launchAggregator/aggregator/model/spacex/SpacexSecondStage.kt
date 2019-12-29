package com.launchAggregator.aggregator.model.spacex

data class SpacexSecondStage(
        val block: Int = 5,
        val payloads: List<SpacexPayload> = listOf(SpacexPayload())
)