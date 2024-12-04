package com.launchAggregator.aggregator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication(scanBasePackages=[
	"com.launchAggregator.aggregator.cache",
	"com.launchAggregator.aggregator.client",
	"com.launchAggregator.aggregator.dao",
	"com.launchAggregator.aggregator.model",
	"com.launchAggregator.aggregator.util",
	"com.launchAggregator.aggregator.web",
	"com.launchAggregator.aggregator.config"
])
@EnableFeignClients
@EnableSwagger2
@EnableScheduling
class AggregatorApplication

fun main(args: Array<String>) {
	runApplication<AggregatorApplication>(*args)
}
