package com.example.conditioning511.domain.script_list.repositories

import androidx.lifecycle.LiveData
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.script_list.models.Script
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ScriptListRepository {
    suspend fun getScripts(): Flow<List<Script>>

    suspend fun getRooms(): Flow<List<Room>>
}