package com.denailry.weatherv2.weather

class LocationBuilder(val name: String) {
    fun build() : Location {
        return Location(name)
    }

    class Location(val name: String)
}