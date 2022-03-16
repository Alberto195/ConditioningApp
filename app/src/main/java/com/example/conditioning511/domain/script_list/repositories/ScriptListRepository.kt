package com.example.conditioning511.domain.script_list.repositories

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.script_list.models.Script
import kotlinx.coroutines.flow.Flow

interface ScriptListRepository {
    suspend fun getScripts(): Flow<List<Script>>

    suspend fun getRooms(): Flow<List<Room>>

    suspend fun getRoomGroups(): Flow<List<ScriptDetailsModel>>
}