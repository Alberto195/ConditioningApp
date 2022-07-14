package com.example.conditioning511.data.script_list.storage.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.conditioning511.data.core.models.ScriptDetailsModel
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

    @Insert
    fun insertRooms(rooms: List<RoomDBModel>)

    @Query("SELECT * FROM script_details")
    fun getRoomGroups(): Flow<List<ScriptDetailsDbJsonModel>>

    @Query("DELETE FROM script_details")
    fun clearDatabase()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScript(scriptGeneralInfoDbModel: ScriptGeneralInfoDbModel)
}