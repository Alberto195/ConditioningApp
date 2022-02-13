package com.example.conditioning511.data.rooms.models

data class Source(
    val co2: Int,
    val dt: String,
    val hum: Int,
    val people: Int?,
    val rid: Int,
    val temp: Int,
    val temp_valve: Int?,
    val name: String,
)