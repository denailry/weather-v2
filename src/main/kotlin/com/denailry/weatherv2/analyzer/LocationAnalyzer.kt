package com.denailry.weatherv2.analyzer

import com.denailry.weatherv2.weather.LocationBuilder

class LocationAnalyzer : Analyzer<String>() {
    private var locations: ArrayList<LocationBuilder.Location> = ArrayList()

    override fun init(locations: ArrayList<LocationBuilder.Location>) {
        this.locations = locations
    }

    override fun filterBy(value: String) {
        val temp = locations

        for (location in temp) {
            if (location.name == value) {
                locations = arrayOf(location).toCollection(ArrayList())
                return
            }
        }

        locations = ArrayList()
    }

    override fun sort(type: SortType) {
        if (type == SortType.ASCENDING) {
            locations.sortWith(compareBy({it.name}))
        } else {
            locations.sortWith(compareByDescending({it.name}))
        }
    }

    override fun result(): ArrayList<LocationBuilder.Location> {
        return locations.toCollection(ArrayList())
    }
}