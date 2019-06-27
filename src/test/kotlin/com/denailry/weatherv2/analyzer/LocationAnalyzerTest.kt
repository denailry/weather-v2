package com.denailry.weatherv2.analyzer

import com.denailry.weatherv2.weather.LocationBuilder
import junit.framework.Assert.assertEquals
import org.junit.Test

class LocationAnalyzerTest {
    @Test
    fun `given location when filtered by cityname should return foo`() {
        val analyzer = LocationAnalyzer()
        val location = LocationBuilder("jakarta").build()

        analyzer.init(arrayOf(location).toCollection(ArrayList()))

        val results = analyzer.result()

        assertEquals(location, results[0])
    }

    @Test
    fun testAnalyzerFilter() {
        val analyzer = LocationAnalyzer()
        val jakarta = LocationBuilder("jakarta").build()
        val bangalore = LocationBuilder("bangalore").build()

        analyzer.init(arrayOf(jakarta, bangalore).toCollection(ArrayList()))
        analyzer.filterBy("bangalore")

        val results = analyzer.result()

        println(results[0].name)

        assertEquals(bangalore, results[0])
    }

    @Test
    fun testAnalyzerSortAscending() {
        val analyzer = LocationAnalyzer()
        val jakarta = LocationBuilder("jakarta").build()
        val bangalore = LocationBuilder("bangalore").build()

        analyzer.init(arrayOf(jakarta, bangalore).toCollection(ArrayList()))
        analyzer.sort(Analyzer.SortType.ASCENDING)

        val results = analyzer.result()

        println(results[0].name)

        assertEquals(bangalore, results[0])
    }

    @Test
    fun testAnalyzerSortDescending() {
        val analyzer = LocationAnalyzer()
        val jakarta = LocationBuilder("jakarta").build()
        val bangalore = LocationBuilder("bangalore").build()

        analyzer.init(arrayListOf(jakarta, bangalore))
        analyzer.sort(Analyzer.SortType.DESCENDING)

        val results = analyzer.result()

        println(results[0].name)

        assertEquals(jakarta, results[0])
    }
}