package com.denailry.weatherv2.analyzer

import com.denailry.weatherv2.weather.LocationBuilder

abstract class Analyzer<T> {
    enum class SortType {
        ASCENDING,
        DESCENDING
    }

    abstract fun init(locations: Array<LocationBuilder.Location>)
    abstract fun filterBy(value: T)
    abstract fun sort(type: SortType)
    abstract fun result() : Array<LocationBuilder.Location>
}