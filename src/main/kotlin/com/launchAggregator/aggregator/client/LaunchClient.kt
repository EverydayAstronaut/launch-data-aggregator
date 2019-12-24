package com.launchAggregator.aggregator.client

import org.springframework.cloud.openfeign.FeignClient


@FeignClient("launches")
interface LaunchClient {
}