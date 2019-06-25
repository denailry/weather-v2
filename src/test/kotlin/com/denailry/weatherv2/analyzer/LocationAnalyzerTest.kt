package com.denailry.weatherv2.analyzer

import com.denailry.weatherv2.weather.LocationBuilder
import junit.framework.Assert.assertEquals
import org.junit.Test

class LocationAnalyzerTest {
    @Test
    fun testInitializedAnalyzer() {
        val analyzer = LocationAnalyzer()
        val location = LocationBuilder("jakarta").build()

        analyzer.init(arrayOf(location))

        val results = analyzer.result()

        assertEquals(location, results[0])
    }
}