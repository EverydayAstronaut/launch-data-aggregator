package com.launchAggregator.aggregator.client

import com.launchAggregator.aggregator.model.launchLibrary.*

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("launches", url="\${app.launchLibrary.url}")
interface LaunchClient {
    @GetMapping("launch/next/50")
    fun getLaunches(): LaunchLibraryLaunchDataWrapper

    @GetMapping("mission")
    fun getMission(@RequestParam("launchid") id: Int): LaunchLibraryMissionDataWrapper

    @GetMapping("missiontype")
    fun getMissionType(): LaunchLibraryMissionTypeDataWrapper

    @GetMapping("agencytype")
    fun getAgencyTypes(@RequestParam("id ") id: Int): LaunchLibraryAgencyTypeDataWrapper
}