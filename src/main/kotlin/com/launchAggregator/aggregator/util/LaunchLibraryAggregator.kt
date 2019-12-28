package com.launchAggregator.aggregator.util

import com.launchAggregator.aggregator.client.LaunchClient
import com.launchAggregator.aggregator.model.launchLibrary.MissionType
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
            val agencyType = launchClient.getAgencyTypes(it.lsp.id).types
            val mission = launchClient.getMission(it.id).missions
            launchDataList.add(when {
                agencyType.isNotEmpty() && mission.isNotEmpty() -> LaunchData(it, mission.first(), it.lsp, agencyType.first())
                agencyType.isNotEmpty() -> LaunchData(it, Mission(), it.lsp, agencyType.first())
                mission.isNotEmpty() -> LaunchData(it, mission.first(), it.lsp, AgencyType())
                else -> LaunchData(it, Mission(), it.lsp, AgencyType())
            })
        }
        return launchDataList
    }
}
