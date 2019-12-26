package com.launchAggregator.aggregator.util

import com.launchAggregator.aggregator.client.LaunchClient
import com.launchAggregator.aggregator.model.MissionType
import com.launchAggregator.aggregator.model.launchLibrary.AgencyType
import com.launchAggregator.aggregator.model.launchLibrary.Launch
import com.launchAggregator.aggregator.model.launchLibrary.LaunchData
import com.launchAggregator.aggregator.model.launchLibrary.Mission
import org.springframework.stereotype.Component

@Component
class LaunchLibraryAggregator(val launchClient: LaunchClient) {
    fun aggregate(launchData: List<Launch>, missionTypeData: List<MissionType>): List<LaunchData> {
        val launchDataList = mutableListOf<LaunchData>()

        launchData.forEach {
            val agency = it.lsp
            val agencyType = launchClient.getAgencyTypes(agency.id).types
            val mission = launchClient.getMission(it.id).missions
            val launchData = when {
                agencyType.isNotEmpty() && mission.isNotEmpty() -> LaunchData(it, mission.first(), agency, agencyType.first())
                agencyType.isNotEmpty() -> LaunchData(it, Mission(), agency, agencyType.first())
                mission.isNotEmpty() -> LaunchData(it, mission.first(), agency, AgencyType())
                else -> LaunchData(it, Mission(), agency, AgencyType())
            }

            launchDataList.add(launchData)
        }

        return launchDataList
    }
}