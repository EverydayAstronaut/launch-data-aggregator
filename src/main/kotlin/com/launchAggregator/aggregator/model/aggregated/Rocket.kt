package com.launchAggregator.aggregator.model.aggregated

data class Rocket(
        val id: Int = 0,
        val name: String = "",
        val familyName: String = "",
        val configuration: String = "",
        val images: Images = Images(),
        var flight: List<Int> = listOf(),
        var gridfins: List<Boolean> = listOf()
)