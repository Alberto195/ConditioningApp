package com.example.conditioning511.domain.script_list.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomGroups(
    val dayGroups: List<DayGroup>?,
    val rIDs: List<Int>?
)