package com.launchAggregator.aggregator.model.launchLibrary

data class AgencyType (
        val id: Int = 0,
        val name: String = ""
)

data class AgencyTypeDataWrapper(
        val types: List<AgencyType> = listOf()
)