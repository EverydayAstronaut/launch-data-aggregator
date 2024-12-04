package com.launchAggregator.aggregator.web

import com.launchAggregator.aggregator.model.LaunchDataPage
import com.launchAggregator.aggregator.model.LaunchDataPageMinimal
import com.launchAggregator.aggregator.util.DateParser
import com.launchAggregator.aggregator.util.LaunchDataAggregator
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/v1/launches")
@CrossOrigin(origins = ["http://localhost:8888", "everydayastronaut.com"])
class LaunchRestController(
        val launchDataAggregator: LaunchDataAggregator,
        val dateParser: DateParser) {
    @ApiOperation(value = "Get all the launches")
    @GetMapping
    fun getLaunchData(
        @ApiParam(value = "Identifier of the launch")
        @RequestParam(required = false) id: Int? = null
    ): LaunchDataPage {

        val launchData = when {
            id == null -> launchDataAggregator.getAllLaunches().map {
                it.ttl = dateParser.getTimeLeft(it.net, LocalDateTime.now(ZoneOffset.UTC), TimeUnit.SECONDS)
                it
            }
            else -> {
                val launch = launchDataAggregator.getIndividualLaunch(id)
                launch.ttl = dateParser.getTimeLeft(launch.net, LocalDateTime.now(ZoneOffset.UTC), TimeUnit.SECONDS)
                listOf(launch)
            }
        }

        return LaunchDataPage(launchData.size, launchData)
    }

    @ApiOperation(value = "Get all the launches with only essential data")
    @GetMapping("/minimal")
    fun getMinimalLaunchData(
            @ApiParam(value = "Identifier of the launch")
            @RequestParam(required = false) id: Int? = null
    ): LaunchDataPageMinimal {
        val launchData = when(id) {
            null -> launchDataAggregator.getMinimalLaunches()
            else -> {
                val launch = launchDataAggregator.getMinimalIndividualLaunch(id)
                launch.ttl = dateParser.getTimeLeft(launch.net, LocalDateTime.now(ZoneOffset.UTC), TimeUnit.SECONDS)
                listOf(launch)
            }
        }

        return LaunchDataPageMinimal(launchData.size, launchData)
    }

    @ApiOperation(value = "Get all the launches of today")
    @GetMapping("/daily")
    fun getDailyLaunches(): LaunchDataPage {
        val launchData = launchDataAggregator.dailyLaunches?: listOf()
        return LaunchDataPage(launchData.size, launchData.map {
            it.ttl = dateParser.getTimeLeft(it.net, LocalDateTime.now(ZoneOffset.UTC), TimeUnit.SECONDS)
            it
        })
    }

}