package com.launchAggregator.aggregator.web

import com.launchAggregator.aggregator.model.LaunchDataPage
import com.launchAggregator.aggregator.model.spacex.SpacexModel
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1/launch")
class LaunchRestController(@Autowired val launchDataAggregator: LaunchDataAggregator) {
    @ApiOperation(value = "Get purchases...", notes = "Get online and offline purchases for the member.")
    @GetMapping
    fun getLauncData(
        @ApiParam(value = "Identifier of the launch")
        @RequestParam(required = false) id: Int? = null
    ): LaunchDataPage {
        val launchData: List<SpacexModel>
        when(id) {
            null -> launchData = launchDataAggregator.getSpacexData()
            else -> launchData = listOf(launchDataAggregator.getSpacexData(id))
        }
        return LaunchDataPage(launchData.size, launchData)
    }
}