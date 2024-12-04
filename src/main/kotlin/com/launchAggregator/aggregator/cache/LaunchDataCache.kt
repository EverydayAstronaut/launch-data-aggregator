package com.launchAggregator.aggregator.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.launchAggregator.aggregator.model.LaunchData
import com.launchAggregator.aggregator.model.MinimalLaunchData
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*
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
        return launchCache.getIfPresent(id)
    }

    fun getAllLaunches(): List<LaunchData>? {
        return launchCache.asMap().values.toList().map { it }
    }

    fun getMinimalIndividualLaunch(id: Int): MinimalLaunchData? {
        val launch = launchCache.getIfPresent(id) ?: return null
        return MinimalLaunchData(
                launch.id,
                launch.name,
                launch.missions.map { mission -> mission.orbit },
                launch.net,
                launch.rocket.agency
        )
    }

    fun getMinimalLaunches(): List<MinimalLaunchData>? {
        val launches =  launchCache.asMap().values.toList()
        return when {
            launches.isEmpty() -> null
            else -> launches.map {
                MinimalLaunchData(
                        it.id,
                        it.name,
                        it.missions.map { mission -> mission.orbit },
                        it.net,
                        it.rocket.agency
                )
            }
        }
    }

    fun addAll(launchData: List<LaunchData>) {
        val mapLaunchData = launchData.associateBy { it.id }
        launchCache.putAll(mapLaunchData)
    }
}