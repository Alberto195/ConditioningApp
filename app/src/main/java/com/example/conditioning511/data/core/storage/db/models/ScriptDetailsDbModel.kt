package com.example.conditioning511.data.core.storage.db.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "script_details")
data class ScriptDetailsDbModel(
    @PrimaryKey(autoGenerate = false)
    val did: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val roomGroups: RoomGroups
) : Serializable {
    data class RoomGroups(
        @ColumnInfo
        val dayGroups: List<DayGroup>,
        @ColumnInfo
        val rIDs: List<Int>
    ) {
        data class DayGroup(
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