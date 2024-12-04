package com.launchAggregator.aggregator.util

import com.launchAggregator.aggregator.model.Orbit
import org.springframework.stereotype.Component

@Component
class OrbitFinder {
    val regexCompleteString = "internationalspacestation|polarorbit|lowearthorbit|mediumearthorbit|geostationarytransferorbit|geostationaryorbit|highlyellipticalorbit|sunsynchronousorbit|sun-synchronousorbit|heliocentricorbit"
    val regexAbbrevString = " iss | po | leo | meo | gto | geo | heo | sso | sso | hco "
    val regexAbbrevStringChar = "iss. | po. | leo. | meo. | gto. | geo. | heo. | sso. | sso. | hco. "
    val regexComplete = Regex(regexCompleteString)
    val regexAbbrev = Regex(regexAbbrevString)
    val regexAbbrevChar = Regex(regexAbbrevStringChar)

    fun find(description: String, orbit: Orbit): Orbit {
        if(orbit != Orbit.UNKNOWN) return orbit
        val descriptionParsed = description.toLowerCase().replace(Regex(" "), "")

        val regexResultAbbrev = regexAbbrev.findAll(description)
        val regexResultAbbrevChar = regexAbbrevChar.findAll(description)
        val regexResultComplete = regexComplete.findAll(descriptionParsed)
        return when {
            regexResultAbbrev.count() > 0 -> {
                val filteredResult = regexResultAbbrev.first().value.replace(Regex("[^a-zA-Z]+"), "")
                return Orbit.valueOf(filteredResult)
            }
            regexResultAbbrevChar.count() > 0 -> {
                val filteredResult = regexResultAbbrevChar.first().value.replace(Regex("[^a-zA-Z]+"), "")
                return Orbit.valueOf(filteredResult)
            }
            regexResultComplete.count() > 0 -> {
                val orbitString = regexResultComplete.first().value
                val key = regexCompleteString.split("|").indexOf(orbitString)
                val orbitAbbrevList = regexAbbrevString.replace(Regex(" "), "").split("|")
                return Orbit.valueOf(orbitAbbrevList[key].toUpperCase())
            }
            else -> Orbit.UNKNOWN
        }
    }
}