package com.launchAggregator.aggregator.util

import com.launchAggregator.aggregator.cache.LaunchCache
import com.launchAggregator.aggregator.cache.SpacexCache
import com.launchAggregator.aggregator.model.launchLibrary.LaunchData
import com.launchAggregator.aggregator.model.spacex.SpacexModel
import org.springframework.stereotype.Service


@Service
class LaunchDataAggregator(private val spacexCache: SpacexCache, private val launchCache: LaunchCache) {
    fun getSpacexData(id: Int? = null) : List<SpacexModel> {
        launchCache.getAllLaunches()
        if(id == null) return spacexCache.getAllLaunches()
        return listOf(spacexCache.getIndividualLaunch(id))
    }

    fun getLaunchLibraryData(id: Int? = null) : List<LaunchData> {
        if(id == null) return launchCache.getAllLaunches()
        return listOf(launchCache.getIndividualLaunch(id))
    }

    fun getLaunchData() {

    }
}