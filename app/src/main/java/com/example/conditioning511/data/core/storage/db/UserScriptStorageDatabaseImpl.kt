package com.example.conditioning511.data.core.storage.db

import com.example.conditioning511.data.core.storage.UserScriptStorageDatabase
import com.example.conditioning511.data.core.storage.db.models.RoomDBModel
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbJsonModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import javax.inject.Inject

class UserScriptStorageDatabaseImpl @Inject constructor(
    private val roomDao: RoomDao
) : UserScriptStorageDatabase {

    override suspend fun setScriptGeneralInfo(scripts: List<ScriptGeneralInfoDbModel>?) {
        scripts?.let { roomDao.insertAllGeneralInfoScripts(it) }
    }

    override suspend fun insertDetailedScript(script: String?, id: Int) {
        script?.let {
            roomDao.insertScript(
                ScriptDetailsDbJsonModel(
                    id = id,
                    script = it
                )
            )
        }
    }

    override suspend fun updateDetailedScript(script: String?, id: Int) {
        script?.let {
            roomDao.updateScript(
                ScriptDetailsDbJsonModel(
                    id = id,
                    script = it
                )
            )
        }
    }

    override suspend fun insertRoom(rooms: List<RoomDBModel>) {
        roomDao.insertRoom(rooms)
    }
}