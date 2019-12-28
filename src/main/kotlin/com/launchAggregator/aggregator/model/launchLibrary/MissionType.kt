package com.launchAggregator.aggregator.model.launchLibrary

data class MissionType(
        val id: Int = 0,
        val name: String? = null
)

data class MissionTypeDataWrapper(
        val types: List<MissionType> = listOf()
)