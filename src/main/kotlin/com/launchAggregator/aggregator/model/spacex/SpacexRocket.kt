package com.launchAggregator.aggregator.model.spacex

data class SpacexRocket(
        val rocket_name: String = "",
        val first_stage: SpacexFirstStage = SpacexFirstStage(),
        val second_stage: SpacexSecondStage = SpacexSecondStage(),
        val fairings: SpacexFairings? = null
)