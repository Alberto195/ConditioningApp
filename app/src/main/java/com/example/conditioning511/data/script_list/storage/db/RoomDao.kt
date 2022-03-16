package com.example.conditioning511.data.script_list.storage.db

import androidx.room.Dao
import androidx.room.Query
import com.example.conditioning511.data.core.storage.db.models.RoomDBModel
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbJsonModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Query("SELECT * FROM scripts_general_info")
    fun getScripts(): Flow<List<ScriptGeneralInfoDbModel>>

    @Query("SELECT * FROM rooms")
    fun getRooms(): Flow<List<RoomDBModel>>

    @Query("SELECT * FROM script_details")
    fun getRoomGroups(): Flow<List<ScriptDetailsDbJsonModel>>
}