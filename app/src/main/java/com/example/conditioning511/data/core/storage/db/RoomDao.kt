package com.example.conditioning511.data.core.storage.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel


// Data Access Object
@Dao
interface RoomDao {
    @Insert
    suspend fun insertAllGeneralInfoScripts(scripts: List<ScriptGeneralInfoDbModel>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScript(script: ScriptDetailsDbModel?)
}
