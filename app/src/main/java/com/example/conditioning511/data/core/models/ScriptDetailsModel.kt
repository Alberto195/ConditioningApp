package com.example.conditioning511.data.core.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ScriptDetailsModel(
    @SerializedName("did")
    val did: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("roomGroups")
    val roomGroups: RoomGroups
) {
    data class RoomGroups(
        @SerializedName("dayGroups")
        val dayGroups: List<DayGroup>,
        @SerializedName("rIDs")
        val rIDs: List<Int>
    ) {
        data class DayGroup(
            @SerializedName("days")
            val days: List<Int>,
            @SerializedName("settings")
            val settings: List<Setting>
        ) {
            data class Setting(
                @SerializedName("at_home")
                val atHome: Int,
                @SerializedName("co2")
                val co2: Int,
                @SerializedName("dont_use")
                val dontUse: List<Any>,
                @SerializedName("hum")
                val hum: Int,
                @SerializedName("must_use")
                val mustUse: List<Any>,
                @SerializedName("mute")
                val mute: Int,
                @SerializedName("temp")
                val temp: Int,
                @SerializedName("time")
                val time: String
            )
        }
    }
}