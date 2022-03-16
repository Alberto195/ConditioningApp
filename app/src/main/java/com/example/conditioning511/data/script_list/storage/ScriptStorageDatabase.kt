package com.example.conditioning511.data.script_list.storage

import androidx.lifecycle.LiveData
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import kotlinx.coroutines.flow.Flow

interface ScriptStorageDatabase {
    suspend fun getScriptsInfo(): Flow<List<ScriptGeneralInfoDbModel>>

    suspend fun getRooms(): Flow<List<Room>>

    suspend fun getRoomGroups(): Flow<List<ScriptDetailsModel>>
}