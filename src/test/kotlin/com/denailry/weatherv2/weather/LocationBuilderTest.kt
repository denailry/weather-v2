package com.denailry.weatherv2.weather

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test

class LocationBuilderTest {
    @Test
    fun testLocationName() {
        val location = LocationBuilder("jakarta").build()
        assertEquals("jakarta", location.name)
    }

    @Test
    fun testNoWeatherInLocation() {
        val location = LocationBuilder("jakarta").build()

        for (day in WeatherDay.values()) {
            assertNull(location.weather(day))
        }
    }

    @Test
    fun testLocationWithWeathers() {
        val weather = Weather(WeatherType.SHINY, WeatherDay.MONDAY, 27.0)
        val location = LocationBuilder("jakarta")
            .addWeather(weather)
            .build()

        assertEquals(weather, location.weather(weather.day))
    }

    @Test
    fun testSetWeatherOfSameDayInLocation() {
        val firstWeather = Weather(WeatherType.SHINY, WeatherDay.MONDAY, 27.0)
        val secondWeather = Weather(WeatherType.RAINY, WeatherDay.MONDAY, 28.0)

        val location = LocationBuilder("jakarta")
            .addWeather(firstWeather)
            .addWeather(secondWeather)
            .build()

        assertEquals(secondWeather, location.weather(WeatherDay.MONDAY))
    }
}