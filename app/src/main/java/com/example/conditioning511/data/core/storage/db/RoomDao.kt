package com.example.conditioning511.data.core.storage.db

import androidx.room.*
import com.example.conditioning511.data.core.storage.db.models.RoomDBModel
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbJsonModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel

@Dao
interface RoomDao {
    @Insert
    suspend fun insertAllGeneralInfoScripts(scripts: List<ScriptGeneralInfoDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScript(script: ScriptDetailsDbJsonModel)

    @Update
    suspend fun updateScript(script: ScriptDetailsDbJsonModel)

    @Insert
    suspend fun insertRoom(rooms: List<RoomDBModel>)
}
