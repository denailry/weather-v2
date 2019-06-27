package com.denailry.weatherv2

import com.denailry.weatherv2.analyzer.DayAnalyzer
import com.denailry.weatherv2.analyzer.LocationAnalyzer
import com.denailry.weatherv2.weather.LocationBuilder
import com.denailry.weatherv2.weather.Weather
import com.denailry.weatherv2.weather.WeatherDay
import com.denailry.weatherv2.weather.WeatherType

class Application {
    private var jakarta = LocationBuilder("jakarta").build()
    private var bangalore = LocationBuilder("bangalore").build()
    private val locationAnalyzer = LocationAnalyzer()
    private val dayAnalyzer = DayAnalyzer()

    companion object {
        @JvmStatic
        fun main(args : Array<String>) {
            val app = Application()
            app.run()
        }
    }

    private fun setupLocation() {
        val jakartaBuilder = LocationBuilder("jakarta")
        val bangaloreBuilder = LocationBuilder("bangalore")

        for (day in WeatherDay.values()) {
            jakartaBuilder.addWeather(Weather(WeatherType.SHINY, day, 0.0))
            bangaloreBuilder.addWeather(Weather(WeatherType.RAINY, day, 1.0))
        }

        jakarta = jakartaBuilder.build()
        bangalore = bangaloreBuilder.build()
    }

    fun run() {
        setupLocation()

        var end = false
        while (end.not()) {
            val input = readLine()

            if (input != null) {
                if (input == "Q") {
                    println("Bye!")
                    end = true
                } else {
                    processInput(input.split(" "))
                }
            }
        }
    }

    private fun processInput(commands: List<String>) {
        locationAnalyzer.init(arrayOf(jakarta, bangalore).toCollection(ArrayList()))
        locationAnalyzer.filterBy(commands[0])

        val results = locationAnalyzer.result()

        if (results.size == 0) {
            println("-- invalid --")
        } else {
            dayAnalyzer.init(results)

            var startingDay: WeatherDay? = null
            when (commands[1]) {
                "monday" -> startingDay = WeatherDay.MONDAY
                "tuesday" -> startingDay = WeatherDay.TUESDAY
                "wednesday" -> startingDay = WeatherDay.WEDNESDAY
                "thursday" -> startingDay = WeatherDay.THURSDAY
                "friday" -> startingDay = WeatherDay.FRIDAY
                "saturday" -> startingDay = WeatherDay.SATURDAY
                "sunday" -> startingDay = WeatherDay.SUNDAY
                else -> {
                    println("-- invalid --")
                }
            }

            if (startingDay != null) {
                dayAnalyzer.filterBy(startingDay)
                showResult(dayAnalyzer.result(), startingDay.value)
            }
        }
    }

    private fun showResult(results: ArrayList<LocationBuilder.Location>, startingDay: Int) {
        var i = 0
        var showedWeathersCount = 0
        for (location in results) {
            while (i < WeatherDay.values().size && showedWeathersCount < 5) {
                val actualIndex = (startingDay + i) % WeatherDay.values().size
                val day = WeatherDay.values()[actualIndex]
                val weather = location.weather(day)
                if (weather != null) {
                    println("day: " + weather.day)
                    println("temperature: " + weather.temperature)
                    println("type: " + weather.type)
                    showedWeathersCount++
                }
                i++
            }
        }
    }
}
