package com.launchAggregator.aggregator.dao

import com.launchAggregator.aggregator.client.SpacexClient
import com.launchAggregator.aggregator.model.*
import com.launchAggregator.aggregator.model.spacex.SpacexCore
import com.launchAggregator.aggregator.model.spacex.SpacexFairings
import com.launchAggregator.aggregator.model.spacex.SpacexPayload
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Service
class SpacexDao(private val spacexClient: SpacexClient) {
    fun getLaunchData(): List<LaunchData> {
        val launchData = spacexClient.getLaunches()
        return launchData.map { spacexModel ->
            val date = when {
                spacexModel.launch_date_unix == null -> LocalDateTime.MIN
                else -> LocalDateTime.ofInstant(Instant.ofEpochMilli(spacexModel.launch_date_unix!!.toLong() * 1000), TimeZone.getTimeZone("618").toZoneId())
            }

            val cores = spacexModel.rocket.first_stage.cores.map { core -> core ?: SpacexCore() }

            val rocket = Rocket(
                    cores = cores.map { core ->
                        Core(
                                core.core_serial ?: "",
                                core.flight,
                                core.block,
                                core.gridfins,
                                core.legs
                        )
                    }
            )

            val missions = spacexModel.rocket.second_stage.payloads.map { Mission(
                    orbit = it.orbit,
                    name = it.payload_id.trim().replace("[^a-zA-Z0-9]", "")
            )}

            val fairings = spacexModel.rocket.fairings ?: SpacexFairings()
            val recovery = Recovery(
                    RecoveryInfo(
                            attempt = fairings.recovery_attempt,
                            location = listOf(fairings.ship),
                            recovered = fairings.recovered
                    ),
                    cores.map { core -> RecoveryInfo(
                            serial = core.core_serial?: "",
                            attempt = core.landing_intent,
                            location = listOf(core.landing_vehicle),
                            recovered = core.land_success,
                            onShip = core.landing_type == "ASDS" || core.landing_type == "OCISLY"
                    )}
            )

            LaunchData(
                    net = date,
                    rocket = rocket,
                    recovery = recovery,
                    missions = missions
            )
        }
    }
}