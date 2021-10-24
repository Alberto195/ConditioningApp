package com.example.conditioning511.data.core.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel

@Database(
    entities = [ScriptGeneralInfoDbModel::class, ScriptDetailsDbModel::class],
    version = 6,
    exportSchema = false
)
abstract class ScriptListRoomDatabase : RoomDatabase() {
    abstract fun getRoomDao(): RoomDao
}
