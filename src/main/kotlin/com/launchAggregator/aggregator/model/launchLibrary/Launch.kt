package com.launchAggregator.aggregator.model.launchLibrary

data class Launch(
        val id: Int = 0,
        val name: String = "",
        val windowstart: String = "",
        val status: Int = 0,
        val tbdtime: Int = 0,
        val tbddate: Int = 0,
        val lsp: Agency = Agency(),
        val rocket: Rocket = Rocket()
)

data class LaunchDataWrapper(
        val launches: List<Launch> = listOf()
)