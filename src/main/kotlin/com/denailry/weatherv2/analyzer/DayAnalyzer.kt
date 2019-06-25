package com.denailry.weatherv2.analyzer

import com.denailry.weatherv2.weather.LocationBuilder
import com.denailry.weatherv2.weather.Weather
import com.denailry.weatherv2.weather.WeatherDay
import kotlin.math.min

class DayAnalyzer : Analyzer<WeatherDay>() {
    private var dumps: ArrayList<Dump> = ArrayList()

    override fun init(locations: Array<LocationBuilder.Location>) {
        dumps = ArrayList()

        for (location in locations) {
            for (day in WeatherDay.values()) {
                val weather = location.weather(day)
                if (weather != null) {
                    dumps.add(Dump(location.name, weather))
                }
            }
        }
    }

    override fun filterBy(value: WeatherDay) {
        this.sort(SortType.ASCENDING)

        var startPos = 0
        for (i in 0..dumps.size-1) {
            val dump = dumps.get(i)
            if (dump.weather.day >= value) {
                startPos = i
                break
            }
        }

        val results = ArrayList<Dump>()
        var index = startPos
        val resultSize = min(dumps.size, 5)

        while (results.size != resultSize) {
            results.add(dumps[index])
            index = (index + 1) % dumps.size
        }

        dumps = results
    }

    override fun sort(type: SortType) {
        if (type == SortType.ASCENDING) {
            dumps.sortWith(compareBy({it.weather.day.value}))
        } else {
            dumps.sortWith(compareByDescending({it.weather.day.value}))
        }
    }

    override fun result(): ArrayList<LocationBuilder.Location> {
        val locationByName = HashMap<String, LocationBuilder>()

        for (dump in dumps) {
            var builder = locationByName.get(dump.location)

            if (builder == null) {
                builder = LocationBuilder(dump.location)
                locationByName.put(dump.location, builder)
            }

            builder.addWeather(dump.weather)
        }

        val locations = ArrayList<LocationBuilder.Location>()

        for (location in locationByName.values) {
            locations.add(location.build())
        }

        return locations
    }

    private open class Dump(val location: String, val weather: Weather)

}