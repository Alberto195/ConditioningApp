package com.example.conditioning511.data.script_list.storage.db

import androidx.room.Dao
import androidx.room.Query
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Query("SELECT * FROM scripts_general_info")
    fun getScripts(): Flow<List<ScriptGeneralInfoDbModel>>

    //TODO переделать
    @Query("SELECT * FROM scripts_general_info")
    fun getRooms(): Flow<List<ScriptListViewModel.Room>>
}