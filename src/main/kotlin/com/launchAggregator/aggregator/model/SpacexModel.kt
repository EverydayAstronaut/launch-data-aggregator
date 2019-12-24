package com.launchAggregator.aggregator.model

import com.launchAggregator.aggregator.model.Orbit.UNKNOWN


data class SpacexModel (
    val flight_number: Int = 0,
    val mission_name: String? = "",
    val details: String = "",
    val upcoming: Boolean = false,
    val launch_date_unix: Int? = null,
    val tbd: Boolean = false,
    val rocket: Rocket = Rocket()
)

data class Rocket(
    val rocket_name: String = "",
    val first_stage: FirstStage = FirstStage(),
    val second_stage: SecondStage = SecondStage(),
    val fairings: Fairings = Fairings()
)

data class FirstStage(
    val cores: List<Core> = listOf(Core())
)

data class Core(
    val core_serial: String? = null,
    val flight: Int = 1,
    val block: Int = 5,
    val gridfins: Boolean = true,
    val legs: Boolean = true,
    val landing_intent: Boolean = true,
    val landing_type: String? = null,
    val landing_vehicle: String? = null
)

data class SecondStage(
    val block: Int = 5,
    val payloads: List<Payload> = listOf(Payload())
)

data class Payload(
    val payload_id: String? = null,
    val payload_mass_kg: Int = 0,
    val orbit: Orbit = UNKNOWN,
    val customers: List<String> = listOf(),
    val payload_type: String? = null,
    val manufacturer: String? = null
)

data class Fairings(
    val reused: Boolean = false,
    val recovery_attempt: Boolean = false,
    val recovered: Boolean = false,
    val ship: String? = null
)