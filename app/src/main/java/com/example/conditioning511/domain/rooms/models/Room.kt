package com.example.conditioning511.domain.rooms.models

data class Room(
    val rId: Int,
    val date: String?,
    val temp: Int?,
    val temp_value: Int?,
    val co2: Int?,
    val hum: Int?,
    val people: Int?,
    var name: String?
)
