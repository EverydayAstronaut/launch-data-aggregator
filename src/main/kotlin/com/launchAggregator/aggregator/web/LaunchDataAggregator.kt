package com.launchAggregator.aggregator.web

import com.launchAggregator.aggregator.cache.SpacexCache
import com.launchAggregator.aggregator.model.spacex.SpacexModel
import org.springframework.stereotype.Service


@Service
class LaunchDataAggregator(private val spacexCache: SpacexCache) {
    fun getSpacexData(id: Int) : SpacexModel {
        return spacexCache.getIndividualLaunch(id)
    }

    fun getSpacexData() : List<SpacexModel> {
        return spacexCache.getAllLaunches()
    }
}