package com.launchAggregator.aggregator.model.launchLibrary

data class Mission(
        val id: Int = 0,
        val name: String = "",
        val description: String = "",
        val type: Int = 0,
        val infoUrl: String = ""
)

data class MissionDataWrapper(
        val missions: List<Mission> = listOf()
)