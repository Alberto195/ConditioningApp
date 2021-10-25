package com.example.conditioning511.data.core.storage.db.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "script_details")
data class ScriptDetailsDbModel(
    @PrimaryKey
    val did: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val roomGroups: RoomGroups,
) {
    data class RoomGroups(
        @ColumnInfo
        val dayGroups: DayGroups,
        @ColumnInfo
        val rIDs: List<Int>
    ) {
        data class DayGroups(
            @ColumnInfo
            val days: List<Int>,
            @ColumnInfo
            val settings: List<Setting>
        ) {
            data class Setting(
                @ColumnInfo
                val atHome: Int,
                @ColumnInfo
                val co2: Int,
                @ColumnInfo
                val dontUse: List<Any>,
                @ColumnInfo
                val hum: Int,
                @ColumnInfo
                val mustUse: List<Any>,
                @ColumnInfo
                val mute: Int,
                @ColumnInfo
                val temp: Int,
                @ColumnInfo
                val time: String
            )
        }
    }
}