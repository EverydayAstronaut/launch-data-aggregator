package com.launchAggregator.aggregator.model.launchLibrary

data class LaunchLibraryModel(
        val launch: LaunchLibraryLaunch = LaunchLibraryLaunch(),
        val mission: LaunchLibraryMission = LaunchLibraryMission(),
        val agency: LaunchLibraryAgency = LaunchLibraryAgency(),
        val agencyType: LaunchLibraryAgencyType = LaunchLibraryAgencyType()
)