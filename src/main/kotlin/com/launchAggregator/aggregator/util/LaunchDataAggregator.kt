package com.launchAggregator.aggregator.util

import com.launchAggregator.aggregator.cache.LaunchDataCache
import com.launchAggregator.aggregator.dao.SpacexDao
import com.launchAggregator.aggregator.dao.LaunchLibraryDao
import com.launchAggregator.aggregator.model.LaunchData
import com.launchAggregator.aggregator.model.Mission
import org.springframework.stereotype.Service


@Service
class LaunchDataAggregator(private val launchDataCache: LaunchDataCache, private val spacexDao: SpacexDao, private val launchLibraryDao: LaunchLibraryDao) {
    fun getAllLaunches(): List<LaunchData> {
        return launchDataCache.getAllLaunches()?: getLaunchDataCronJob()
    }

    fun getIndividualLaunches(id: Int): LaunchData {
        return launchDataCache.getIndividualLaunch(id)?: getLaunchDataCronJob().find { it.id == id } ?: LaunchData()
    }

    fun getLaunchDataCronJob(): List<LaunchData> {
        val spacexDataList = spacexDao.getLaunchData()
        val launchLibraryData = launchLibraryDao.getLaunchData()

        val launchLibraryList = launchLibraryData.map { launchData ->
            if(launchData.rocket.agency.short == "SpX") {
                val launch = spacexDataList.find { it.net.year == launchData.net.year && it.net.month == launchData.net.month && it.net.dayOfMonth == launchData.net.dayOfMonth  }
                when {
                    launchData.missions.isEmpty() && launch != null -> {
                        launchData.missions = launch.missions
                    }
                    launch != null -> {
                        val missions = mutableListOf<Mission>()

                        for((k,v) in launchData.missions.withIndex()) {
                            v.orbit = launch.missions[k].orbit
                            missions.add(v)
                        }

                        launchData.missions = missions
                    }
                }

                if(launch != null) {
                    val missions = mutableListOf<Mission>()

                    for((k,v) in launchData.missions.withIndex()) {
                        v.orbit = launch.missions[k].orbit
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
        return launchLibraryList
    }
}