package com.denailry.weatherv2.weather

enum class WeatherType(val value: Int) {
    SHINY(1) {
        override fun toString(): String = "shiny"
    },
    RAINY(2) {
        override fun toString(): String = "rainy"
    }
}