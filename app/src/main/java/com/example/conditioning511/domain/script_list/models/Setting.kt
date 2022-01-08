package com.example.conditioning511.domain.script_list.models

data class Setting(
    val at_home: Int?,
    val co2: Int?,
    val dont_use: List<Any>?,
    val hum: Int?,
    val must_use: List<Any>?,
    val mute: Int?,
    val temp: Int?,
    val time: String?
)