package com.example.conditioning511.data.core.storage.db.converters

import androidx.room.TypeConverter
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbModel

class RoomGroupsConverter {
    @TypeConverter
    fun fromRoomGroups(roomGroups: ScriptDetailsDbModel.RoomGroups) {
        var ids = ""
        var dayGroups = ""
        for (i in roomGroups.rIDs) ids += "$i,"
    }

}