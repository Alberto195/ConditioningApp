package com.example.conditioning511.data.core.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbJsonModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel

@Database(
    entities = [ScriptGeneralInfoDbModel::class, ScriptDetailsDbJsonModel::class],
    version = 8,
    exportSchema = false
)
abstract class ScriptListRoomDatabase : RoomDatabase() {
    abstract fun getRoomDao(): RoomDao
}
