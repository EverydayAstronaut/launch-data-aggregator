package com.launchAggregator.aggregator.model

data class Rocket(
        val id: Int = 0,
        val name: String = "",
        val family: String = "",
        val configuration: String = "",
        val country: String = "",
        var cores: List<Core> = listOf(),
        val agency: Agency = Agency(),
        val image: Image = Image()
)