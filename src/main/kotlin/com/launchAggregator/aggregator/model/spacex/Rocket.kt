package com.launchAggregator.aggregator.model.spacex

data class Rocket(
        val rocket_name: String = "",
        val first_stage: FirstStage = FirstStage(),
        val second_stage: SecondStage = SecondStage(),
        val fairings: Fairings = Fairings()
)