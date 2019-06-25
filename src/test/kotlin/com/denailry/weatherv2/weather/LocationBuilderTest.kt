package com.denailry.weatherv2.weather

import junit.framework.Assert.assertEquals
import org.junit.Test

class LocationBuilderTest {
    @Test
    fun testLocationName() {
        val location = LocationBuilder("jakarta").build()
        assertEquals("jakarta", location.name)
    }
}