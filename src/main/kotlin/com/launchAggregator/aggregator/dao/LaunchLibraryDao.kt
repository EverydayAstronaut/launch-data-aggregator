package com.launchAggregator.aggregator.dao

import com.launchAggregator.aggregator.client.LaunchClient
import com.launchAggregator.aggregator.model.*
import com.launchAggregator.aggregator.util.DateParser
import com.launchAggregator.aggregator.util.OrbitFinder
import org.springframework.stereotype.Service

@Service
class LaunchLibraryDao(private val launchClient: LaunchClient, private val dateParser: DateParser, private val orbitFinder: OrbitFinder) {
    fun getLaunchData(): List<LaunchData> {
        val launchData = launchClient.getLaunches().launches
        val missionTypes = launchClient.getMissionType().types

        return launchData.map {
            val location = Location(
                    it.location.name,
                    it.location.countryCode,
                    it.location.pads.first()
            )

            val missions = it.missions.map { mission ->
                val type = missionTypes.find { it.id  == mission.type}?.name ?: "Unknown"

                Mission(
                        mission.id,
                        mission.name,
                        type,
                        mission.description,
                        orbitFinder.find(mission.description, Orbit.UNKNOWN)
                )
            }

            val agency = Agency(
                    it.lsp.name,
                    it.lsp.abbrev,
                    it.lsp.type,
                    it.lsp.islsp,
                    it.lsp.countryCode
            )

            val rocket = Rocket(
                    id = it.rocket.id,
                    name = it.rocket.name,
                    family = it.rocket.familyname,
                    configuration = it.rocket.configuration,
                    agency = agency,
                    image = Image(it.rocket.imageURL, it.rocket.imageSizes)
            )

            LaunchData(
                    it.id,
                    it.name,
                    dateParser.toLocalDateTime(it.windowstart),
                    it.status,
                    location,
                    missions,
                    rocket,
                    Recovery()
            )
        }
    }
}