package com.launchAggregator.aggregator.web

import com.launchAggregator.aggregator.model.LaunchDataPage
import com.launchAggregator.aggregator.model.LaunchDataPageMinimal
import com.launchAggregator.aggregator.util.LaunchDataAggregator
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/launch")
class LaunchRestController(@Autowired val launchDataAggregator: LaunchDataAggregator) {
    @ApiOperation(value = "Get all the launches")
    @GetMapping
    fun getLaunchData(
        @ApiParam(value = "Identifier of the launch")
        @RequestParam(required = false) id: Int? = null
    ): LaunchDataPage {
        val launchData = when {
            id == null -> launchDataAggregator.getAllLaunches()
            else -> listOf(launchDataAggregator.getIndividualLaunch(id))
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
            else ->  listOf(launchDataAggregator.getMinimalIndividualLaunch(id))
        }

        return LaunchDataPageMinimal(launchData.size, launchData)
    }

    @ApiOperation(value = "Get all the launches of today")
    @GetMapping("/daily")
    fun getDailyLaunches(): LaunchDataPage {
        val launchData = launchDataAggregator.dailyLaunches?: listOf()
        return LaunchDataPage(launchData.size, launchData)
    }
}