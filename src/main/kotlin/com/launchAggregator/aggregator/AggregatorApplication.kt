package com.launchAggregator.aggregator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication(scanBasePackages=[
	"com.launchAggregator.aggregator.cache",
	"com.launchAggregator.aggregator.client",
	"com.launchAggregator.aggregator.dao",
	"com.launchAggregator.aggregator.model",
	"com.launchAggregator.aggregator.util",
	"com.launchAggregator.aggregator.web"
])
@EnableFeignClients
@EnableScheduling
class AggregatorApplication

fun main(args: Array<String>) {
	runApplication<AggregatorApplication>(*args)
}
