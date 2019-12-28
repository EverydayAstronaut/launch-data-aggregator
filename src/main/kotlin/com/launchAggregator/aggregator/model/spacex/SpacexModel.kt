package com.launchAggregator.aggregator.model.spacex

data class SpacexModel (
    val flight_number: Int = 0,
    val mission_name: String? = "",
    val details: String = "",
    val upcoming: Boolean = false,
    val launch_date_unix: Int? = null,
    val tbd: Boolean = false,
    val rocket: Rocket = Rocket()
)