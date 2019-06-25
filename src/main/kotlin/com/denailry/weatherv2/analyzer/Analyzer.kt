package com.denailry.weatherv2.analyzer

import com.denailry.weatherv2.weather.LocationBuilder

abstract class Analyzer<T> {
    companion object {
        val SORT_ASCENDING = 1
        val SORT_DESCENDING = -1
    }

    abstract fun init(locations: Array<LocationBuilder.Location>)
    abstract fun filterBy(value: T)
    abstract fun sort()
    abstract fun result() : Array<LocationBuilder.Location>
}