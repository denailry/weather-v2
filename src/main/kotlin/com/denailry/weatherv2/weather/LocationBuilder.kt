package com.denailry.weatherv2.weather

class LocationBuilder(val name: String) {
    private val weathers = arrayOfNulls<Weather>(WeatherDay.values().size)

    fun build() : Location {
        return Location(name, weathers)
    }

    fun addWeather(weather: Weather) : LocationBuilder {
        weathers[weather.day.value] = weather
        return this
    }

    class Location(val name: String, private val weathers: Array<Weather?>) {
        fun weather(day: WeatherDay) : Weather? {
            return weathers[day.value]
        }
    }
}