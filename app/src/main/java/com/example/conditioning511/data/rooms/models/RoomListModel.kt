package com.example.conditioning511.data.rooms.models

import com.google.gson.annotations.SerializedName

data class RoomListModel(
    @SerializedName("sources")
    val sources: List<Source>
)