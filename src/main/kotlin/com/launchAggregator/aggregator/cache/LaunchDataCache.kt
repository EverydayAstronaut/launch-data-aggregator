package com.launchAggregator.aggregator.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.launchAggregator.aggregator.model.LaunchData
import com.launchAggregator.aggregator.model.MinimalLaunchData
import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class LaunchDataCache {
    private val launchCache = Caffeine.newBuilder()
            .maximumWeight(1000000)
            .expireAfterWrite(24, TimeUnit.HOURS)
            .weigher { _: Int, value: LaunchData -> value.toString().length }
            .executor(Executors.newCachedThreadPool())
            .recordStats()
            .build<Int, LaunchData>()

    fun getIndividualLaunch(id: Int): LaunchData? {
        return launchCache.getIfPresent(id)?: null
    }

    fun getAllLaunches(): List<LaunchData>? {
        val entries = launchCache.asMap().values.toList()
        return when {
            entries.isEmpty() -> null
            else -> entries
        }
    }

    // TODO: LOOK AT WHAT IS NECESSARY IN THIS MODEL. THIS HAS YET TO BE DETERMINED
    fun getAllLaunchesListView(): List<MinimalLaunchData> {
        val launches =  launchCache.asMap().values.toList()
        return launches.map { MinimalLaunchData(
                it.id,
                it.name,
                it.missions.map { mission -> mission.orbit },
                it.net,
                it.rocket.agency
        ) }
    }

    fun addAll(launchData: List<LaunchData>) {
        val mapLaunchData = launchData.associateBy { it.id }
        launchCache.putAll(mapLaunchData)
    }
}