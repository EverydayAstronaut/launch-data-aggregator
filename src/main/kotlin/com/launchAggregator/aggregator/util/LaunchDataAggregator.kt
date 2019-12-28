package com.launchAggregator.aggregator.util

import com.launchAggregator.aggregator.cache.LaunchCache
import com.launchAggregator.aggregator.cache.SpacexCache
import com.launchAggregator.aggregator.model.aggregated.*
import com.launchAggregator.aggregator.model.launchLibrary.LaunchData
import com.launchAggregator.aggregator.model.spacex.SpacexModel
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.*
import java.util.TimeZone




@Service
class LaunchDataAggregator(private val spacexCache: SpacexCache, private val launchCache: LaunchCache) {
    fun getSpacexData(dateTime: LocalDateTime? = null) : List<SpacexModel> {
        if(dateTime == null) return spacexCache.getAllLaunches()
        return listOf(spacexCache.getIndividualLaunch(dateTime))
    }

    fun getLaunchLibraryData(id: Int? = null) : List<LaunchData> {
        if(id == null) return launchCache.getAllLaunches()
        return listOf(launchCache.getIndividualLaunch(id))
    }

    /**
     * Aggregator for the launchlibrary data and the spacex data, morphing the existing models to more optimized.
     * However this is an expensive function at this point. Needs to be either optimized or the combined result be cached for a quick request
     */
    fun getLaunchData(id: Int? = null): List<AggregatedLaunchData> {
        val launchLibraryData = getLaunchLibraryData(id)
        val launchDataList: MutableList<AggregatedLaunchData> = mutableListOf()

        launchLibraryData.forEach { it ->
            val formatter = SimpleDateFormat("MMMM dd, yyyy HH:mm:ss Z")
            formatter.timeZone = TimeZone.getTimeZone("618")
            val date = formatter.parse(it.launch.windowstart).toInstant()
                    .atZone(ZoneOffset.UTC)
                    .toLocalDateTime()

            val mission = Mission(it.mission.name, it.mission.description)
            var reusable = Reusable()
            var rocket = Rocket(
                    it.launch.rocket.id,
                    it.launch.rocket.name,
                    it.launch.rocket.familyname,
                    it.launch.rocket.configuration,
                    Images(it.launch.rocket.imageURL, it.launch.rocket.imageSizes)
            )

            if(it.agency.abbrev == "SpX") {
                val spacexData = getSpacexData(date).first()
                val listLanding = mutableListOf<Landing>()
                spacexData.rocket.first_stage.cores.forEach {
                    listLanding.add(Landing(
                            it.landing_intent,
                            it.landing_type,
                            it.landing_vehicle,
                            it.flight
                    ))
                }

                rocket.flight = spacexData.rocket.first_stage.cores.map { core -> core.flight }
                rocket.gridfins = spacexData.rocket.first_stage.cores.map{ core -> core.gridfins }
                reusable.fairings = spacexData.rocket.fairings
                reusable.landing = listLanding
            }

            launchDataList.add(AggregatedLaunchData(
                    it.launch.id,
                    it.launch.name,
                    date,
                    it.launch.status,
                    it.launch.tbdtime,
                    it.launch.tbddate,
                    mission,
                    it.launch,
                    reusable,
                    rocket
            ))
        }

        return launchDataList.toList()
    }
}