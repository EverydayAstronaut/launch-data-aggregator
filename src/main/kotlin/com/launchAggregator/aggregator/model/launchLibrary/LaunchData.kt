package com.launchAggregator.aggregator.model.launchLibrary

data class LaunchData(
        val launch: Launch = Launch(),
        val mission: Mission = Mission(),
        val agency: Agency = Agency(),
        val agencyType: AgencyType = AgencyType()
)