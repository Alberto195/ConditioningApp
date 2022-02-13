package com.example.conditioning511.domain.rooms.models

import com.google.gson.annotations.SerializedName

data class RoomName(
    @SerializedName("r_name")
    val name: String,
    val rid: Int
)