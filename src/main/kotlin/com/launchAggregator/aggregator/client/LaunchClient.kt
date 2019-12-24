package com.launchAggregator.aggregator.client

import com.launchAggregator.aggregator.model.Agency
import com.launchAggregator.aggregator.model.AgencyType
import com.launchAggregator.aggregator.model.MissionType
import com.launchAggregator.aggregator.model.launchLibrary.Mission
import com.launchAggregator.aggregator.model.launchLibrary.Launch

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient("launches", url="\${app.launchLibrary.base}")
interface LaunchClient {
    @GetMapping("\${app.launchLibrary.sub.launch}")
    fun getLaunches(): List<Launch>

    @GetMapping("\${app.launchLibrary.sub.mission}")
    fun getMission(@RequestParam("launchid") id: String): Mission

    @GetMapping("\${app.launchLibrary.sub.missionType}")
    fun getMissionType(): List<MissionType>

    @GetMapping("\${app.launchLibrary.sub.agency}")
    fun getAgencies(@RequestParam("launchid") id: String): Agency

    @GetMapping("\${app.launchLibrary.sub.agencyType}")
    fun getAgencyTypes(@RequestParam("launchid") id: String): List<AgencyType>
}