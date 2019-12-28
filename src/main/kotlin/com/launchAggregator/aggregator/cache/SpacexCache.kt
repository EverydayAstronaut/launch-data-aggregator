package com.launchAggregator.aggregator.cache

import com.launchAggregator.aggregator.client.SpacexClient
import com.launchAggregator.aggregator.model.spacex.SpacexModel
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.stereotype.Service
import java.security.Timestamp
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import java.util.concurrent.Executors
import java.util.TimeZone
import java.time.Instant



@Service
class SpacexCache(private val spacexClient: SpacexClient) {
    private val cacheInstance = Caffeine.newBuilder()
            .maximumWeight(1000000)
            .expireAfterAccess(2, TimeUnit.HOURS)
            .weigher { _: String, value: List<SpacexModel> -> value.size }
            .executor(Executors.newCachedThreadPool())
            .recordStats()
            .build<String, List<SpacexModel>>()

    fun getIndividualLaunch(dateTime: LocalDateTime): SpacexModel {
        val launches = getLaunches()
        return launches.find {
            val time = LocalDateTime.ofInstant(Instant.ofEpochMilli(it.launch_date_unix!!.toLong() * 1000), TimeZone.getTimeZone("618").toZoneId())
            (time.dayOfMonth == dateTime.dayOfMonth && time.month == dateTime.month && time.year == dateTime.year)
        }?: SpacexModel()
    }

    fun getAllLaunches(): List<SpacexModel> {
        return getLaunches()
    }

    private fun getLaunches(): List<SpacexModel> {
        return cacheInstance.getIfPresent("launch") ?: getFromClient()
    }

    private fun getFromClient(): List<SpacexModel> {
        val launches = spacexClient.getRelaunches().reversed()
        cacheInstance.put("launch", launches)
        return launches
    }
}