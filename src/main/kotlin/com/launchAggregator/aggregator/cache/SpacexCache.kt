package com.launchAggregator.aggregator.cache

import com.launchAggregator.aggregator.client.SpacexClient
import com.launchAggregator.aggregator.model.spacex.SpacexModel
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import java.util.concurrent.Executors

@Service
class SpacexCache(private val spacexClient: SpacexClient) {
    private val cacheInstance = Caffeine.newBuilder()
            .maximumWeight(1000000)
            .expireAfterAccess(2, TimeUnit.HOURS)
            .weigher { _: String, value: List<SpacexModel> -> value.size }
            .executor(Executors.newCachedThreadPool())
            .recordStats()
            .build<String, List<SpacexModel>>()

    fun getIndividualLaunch(id: Int): SpacexModel {
        val launches = getLaunches()
        return launches.first()
//        return launches.find { it.flight_number == id }?: SpacexModel()
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