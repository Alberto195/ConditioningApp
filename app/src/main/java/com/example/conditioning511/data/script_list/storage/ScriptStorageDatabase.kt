package com.example.conditioning511.data.script_list.storage

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.domain.rooms.models.Room
import kotlinx.coroutines.flow.Flow

interface ScriptStorageDatabase {
    suspend fun getScriptsInfo(): Flow<List<ScriptGeneralInfoDbModel>>

    suspend fun insertScripInfo(scriptGeneralInfo: ScriptGeneralInfoDbModel)

    suspend fun getRooms(): Flow<List<Room>>

    suspend fun insertRooms(list: List<Room>)

    suspend fun clearDatabase()

    suspend fun getRoomGroups(): Flow<List<ScriptDetailsModel>>
}