package com.example.conditioning511.data.core.storage.db

import com.example.conditioning511.data.core.storage.UserScriptStorageDatabase
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel

class UserScriptStorageDatabaseImpl(
    private val roomDao: RoomDao
) : UserScriptStorageDatabase {

    override suspend fun setScriptGeneralInfo(scripts: List<ScriptGeneralInfoDbModel>?) {
        roomDao.insertAllGeneralInfoScripts(scripts)
    }

    override suspend fun insertDetailedScript(script: ScriptDetailsDbModel?) {
        roomDao.insertScript(script)
    }
}