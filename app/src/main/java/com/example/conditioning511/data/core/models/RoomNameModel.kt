package com.example.conditioning511.data.core.models

import com.google.gson.annotations.SerializedName

data class RoomNameModel(
    @SerializedName("rooms")
    val rooms: List<Room>
)