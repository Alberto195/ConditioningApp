package com.example.conditioning511.data.script_list.storage.db

import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.data.script_list.storage.ScriptStorageDatabase
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScriptStorageDatabaseImpl @Inject constructor(
    private val roomDao: RoomDao
) : ScriptStorageDatabase {
    override suspend fun getScriptsInfo(): Flow<List<ScriptGeneralInfoDbModel>> {
        return roomDao.getScripts()
    }

    override suspend fun getRooms(): Flow<List<ScriptListViewModel.Room>> {
        return roomDao.getRooms()
    }
}