package com.example.conditioning511.data.script_list.repositories

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.data.script_list.storage.ScriptStorageDatabase
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.script_list.models.Script
import com.example.conditioning511.domain.script_list.repositories.ScriptListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScriptListRepositoryImpl @Inject constructor (
    private val scriptStorageDatabase: ScriptStorageDatabase,
) : ScriptListRepository {
    override suspend fun getScripts(): Flow<List<Script>> {
        return scriptStorageDatabase.getScriptsInfo().map { scripts ->
            scripts.map { script ->
                Script(
                    isCurrent = script.isCurrent,
                    scId = script.scId,
                    name = script.name
                )
            }
        }
    }

    // TODO заменить на нормальный рум
    override suspend fun getRooms(): Flow<List<Room>> {
        return scriptStorageDatabase.getRooms()
    }

    override suspend fun insertRooms(rooms: List<Room>) {
        scriptStorageDatabase.insertRooms(rooms)
    }

    override suspend fun getRoomGroups(): Flow<List<ScriptDetailsModel>> {
        return scriptStorageDatabase.getRoomGroups()
    }

    override suspend fun insertScriptGeneral(script: ScriptGeneralInfoDbModel) {
        scriptStorageDatabase.insertScripInfo(script)
    }

    override suspend fun clearDatabase() {
        scriptStorageDatabase.clearDatabase()
    }
}