package com.launchAggregator.aggregator.model.launchLibrary

data class Launch(
        val id: Int = 0,
        val name: String = "",
        val net: String = "",
        val status: Int = 0,
        val tbdtime: Int = 0,
        val tbddate: Int = 0,
        val lsp: Agency = Agency()
)

data class LaunchDataWrapper(
        val launches: List<Launch> = listOf()
)