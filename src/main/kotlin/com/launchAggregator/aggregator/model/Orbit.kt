package com.launchAggregator.aggregator.model

import com.fasterxml.jackson.annotation.JsonProperty

enum class Orbit {
    UNKNOWN,
    MEO,
    GEO,
    LEO,
    HEO,
    VLEO,
    ISS,
    GTO,
    SSO,
    SO,
    PO,
    HCO,
    @JsonProperty("ES-L1") ESL1
}