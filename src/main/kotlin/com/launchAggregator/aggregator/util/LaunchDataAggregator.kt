package com.launchAggregator.aggregator.util

import com.launchAggregator.aggregator.cache.LaunchDataCache
import com.launchAggregator.aggregator.dao.SpacexDao
import com.launchAggregator.aggregator.dao.LaunchLibraryDao
import com.launchAggregator.aggregator.model.LaunchData
import com.launchAggregator.aggregator.model.MinimalLaunchData
import com.launchAggregator.aggregator.model.Mission
import com.launchAggregator.aggregator.model.Orbit
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.TimeUnit


private val log = KotlinLogging.logger {}

@Service
class LaunchDataAggregator(private val launchDataCache: LaunchDataCache, private val spacexDao: SpacexDao, private val launchLibraryDao: LaunchLibraryDao, private val dateParser: DateParser, private val orbitFinder: OrbitFinder) {
    var dailyLaunches: List<LaunchData>? = null

    fun getAllLaunches(): List<LaunchData> {
        return launchDataCache.getAllLaunches()?: getLaunchDataCronJob()
    }

    fun getIndividualLaunch(id: Int): LaunchData {
        return launchDataCache.getIndividualLaunch(id)?: getLaunchDataCronJob().find { it.id == id }?: LaunchData()
    }

    fun getMinimalIndividualLaunch(id: Int): MinimalLaunchData {
        return launchDataCache.getMinimalIndividualLaunch(id) ?: when (val launchNew = getLaunchDataCronJob().find { it.id == id }) {
            null -> MinimalLaunchData()
            else -> MinimalLaunchData(
                    launchNew.id,
                    launchNew.name,
                    launchNew.missions.map { it.orbit },
                    launchNew.net,
                    launchNew.rocket.agency
            )
        }
    }

    fun getMinimalLaunches(): List<MinimalLaunchData> {
        return launchDataCache.getMinimalLaunches()?: getLaunchDataCronJob().map {
            MinimalLaunchData(
                    it.id,
                    it.name,
                    it.missions.map { it.orbit },
                    it.net,
                    it.rocket.agency
            )
        }
    }

    @Scheduled(fixedDelay = 3600000)
    private fun getLaunchDataCronJob(): List<LaunchData> {
        log.info("executing cronjob")

        val spacexDataList = spacexDao.getLaunchData()
        val launchLibraryData = launchLibraryDao.getLaunchData()

        val launchLibraryList = launchLibraryData.map { launchData ->
            if(launchData.rocket.agency.short == "SpX") {
                val launch = spacexDataList.find { it.net.year == launchData.net.year && it.net.month == launchData.net.month && it.net.dayOfMonth == launchData.net.dayOfMonth  }
                when {
                    launchData.missions.isEmpty() && launch != null ->   {
                        launchData.missions = launch.missions
                    }
                    launch != null -> {
                        val missions = mutableListOf<Mission>()

                        for((k,v) in launchData.missions.withIndex()) {
                            v.orbit = orbitFinder.find(launch.missions[k].description, launch.missions[k].orbit)
                            missions.add(v)
                        }

                        launchData.missions = missions
                    }
                }

                if(launch != null) {
                    val missions = mutableListOf<Mission>()

                    for((k,v) in launchData.missions.withIndex()) {
                        v.orbit = orbitFinder.find(launch.missions[k].description, launch.missions[k].orbit)
                        missions.add(v)
                    }

                    launchData.missions = missions
                }


                val spacexData = spacexDataList.find { it.net.month == launchData.net.month && it.net.dayOfMonth == launchData.net.dayOfMonth }?: LaunchData()
                launchData.rocket.cores = spacexData.rocket.cores
                launchData.recovery = spacexData.recovery
                launchData.missions
            }

            launchData
        }

        launchDataCache.addAll(launchLibraryList)
        log.info("finished executing cronjob")
        return launchLibraryList
    }

    @Scheduled(fixedDelay = 43200000, initialDelay = 10000)
    fun checkDailyLaunches() {
        log.info("checking daily launches")
        val launches = launchDataCache.getAllLaunches()
        dailyLaunches = launches?.filter { it.net.dayOfMonth == LocalDateTime.now(ZoneOffset.UTC).dayOfMonth }
        log.info("these are the daily launches: ${dailyLaunches?.map { it.name }}")
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 15000)
    fun validateNet() {
        dailyLaunches?.forEach {
            log.info("validating net")
            val timeLeftMinutes = dateParser.getTimeLeft(it.net, LocalDateTime.now(ZoneOffset.UTC), TimeUnit.MINUTES)
            if (timeLeftMinutes <= 60) {
                getLaunchDataCronJob()
            }
            log.info("net validated")
        }
    }
}