package com.example.conditioning511.data.core.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbJsonModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel

@Database(
    entities = [ScriptGeneralInfoDbModel::class, ScriptDetailsDbJsonModel::class],
    version = 9,
    exportSchema = false
)
abstract class ScriptRoomDatabase : RoomDatabase() {
    abstract fun getRoomDao(): RoomDao

    abstract fun getRoomDaoScriptList(): com.example.conditioning511.data.script_list.storage.db.RoomDao
}
