package com.launchAggregator.aggregator.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.launchAggregator.aggregator.client.LaunchClient
import com.launchAggregator.aggregator.model.launchLibrary.LaunchData
import com.launchAggregator.aggregator.util.LaunchLibraryAggregator
import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class LaunchCache(val launchClient: LaunchClient, val launchLibraryAggregator: LaunchLibraryAggregator) {
    private val launchCache = Caffeine.newBuilder()
            .maximumWeight(1000000)
            .expireAfterWrite(6, TimeUnit.HOURS)
            .weigher { _: Int, value: LaunchData -> value.toString().length }
            .executor(Executors.newCachedThreadPool())
            .recordStats()
            .build<Int, LaunchData>()

    fun getIndividualLaunch(id: Int): LaunchData {
        val launches = getLaunches()
        return launches.find { it.mission.id == id }?: LaunchData()
    }

    fun getAllLaunches(): List<LaunchData> {
        return getLaunches()
    }

    private fun getLaunches(): List<LaunchData> {
        val entries = launchCache.asMap().values.toList()
        if(entries.size == 50) return entries
        return getFromClient()
    }

    private fun getFromClient(): List<LaunchData> {
        val launchData = launchClient.getLaunches().launches
        val missionTypeData = launchClient.getMissionType().types
        return launchLibraryAggregator.aggregate(launchData, missionTypeData).onEach {
            launchCache.put(it.launch.id, it)
        }
    }
}