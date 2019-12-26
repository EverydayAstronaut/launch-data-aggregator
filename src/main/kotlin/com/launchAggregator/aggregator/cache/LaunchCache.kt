package com.launchAggregator.aggregator.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.launchAggregator.aggregator.client.LaunchClient
import com.launchAggregator.aggregator.model.MissionType
import com.launchAggregator.aggregator.model.launchLibrary.Launch
import com.launchAggregator.aggregator.model.spacex.SpacexModel
import com.launchAggregator.aggregator.util.LaunchLibraryAggregator
import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class LaunchCache(val launchClient: LaunchClient, val launchLibraryAggregator: LaunchLibraryAggregator) {
    private val missionCache = Caffeine.newBuilder()
            .maximumWeight(1000000)
            .expireAfterWrite(6, TimeUnit.HOURS)
            .weigher { _: String, value: List<SpacexModel> -> value.size }
            .executor(Executors.newCachedThreadPool())
            .recordStats()
            .build<String, List<SpacexModel>>()

    private val agencyCache = Caffeine.newBuilder()
            .maximumWeight(1000000)
            .expireAfterWrite(2, TimeUnit.DAYS)
            .weigher { _: String, value: List<SpacexModel> -> value.size }
            .executor(Executors.newCachedThreadPool())
            .recordStats()
            .build<String, List<SpacexModel>>()

    private val launchCache = Caffeine.newBuilder()
            .maximumWeight(1000000)
            .expireAfterWrite(6, TimeUnit.HOURS)
            .weigher { _: String, value: List<SpacexModel> -> value.size }
            .executor(Executors.newCachedThreadPool())
            .recordStats()
            .build<String, List<SpacexModel>>()

    private val missionTypeCache = Caffeine.newBuilder()
            .maximumWeight(1000000)
            .expireAfterWrite(6, TimeUnit.HOURS)
            .weigher { _: String, value: List<SpacexModel> -> value.size }
            .executor(Executors.newCachedThreadPool())
            .recordStats()
            .build<String, List<SpacexModel>>()

    private val agencyTypeCache = Caffeine.newBuilder()
            .maximumWeight(1000000)
            .expireAfterWrite(6, TimeUnit.HOURS)
            .weigher { _: String, value: List<SpacexModel> -> value.size }
            .executor(Executors.newCachedThreadPool())
            .recordStats()
            .build<String, List<SpacexModel>>()

    fun getIndividualLaunch(id: Int): SpacexModel {
        val launches = getLaunches()
        return launches.find { it.flight_number == id }?: SpacexModel()
    }

    fun getAllLaunches(): List<SpacexModel> {
        return getLaunches()
    }

    private fun getLaunches(): List<SpacexModel> {
        getFromClient()


        return listOf(SpacexModel())
//        val y = launchCache.asMap()
//        return y.values.first()
//        return launchCache.asMap().values. ?: getFromClient()

        // todo: LOOK AT DIFFERENT POSSIBILITIES IN REGARDS TO CACHING SINCE I NOW CACHE EACH PART INDIVIDUALLY BUT RATHER WOULD CACHE EACH LAUNCH(RESULT)
    }

    private fun getFromClient(): List<SpacexModel> {
        val launchData = launchClient.getLaunches().launches
        val missionTypeData = launchClient.getMissionType().types
        val launches = launchLibraryAggregator.aggregate(launchData, missionTypeData)
//        launchCache.put("launches", launches)
        return listOf(SpacexModel())
    }
}