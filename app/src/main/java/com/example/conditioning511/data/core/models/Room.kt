package com.example.conditioning511.data.core.models

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("r_name")
    val name: String,
    val rid: Int
)