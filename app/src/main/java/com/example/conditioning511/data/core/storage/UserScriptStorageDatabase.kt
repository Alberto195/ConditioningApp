package com.example.conditioning511.data.core.storage

import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel

interface UserScriptStorageDatabase {
    suspend fun setScriptGeneralInfo(scripts: List<ScriptGeneralInfoDbModel>?)

    suspend fun insertDetailedScript(script: ScriptDetailsDbModel?)
}