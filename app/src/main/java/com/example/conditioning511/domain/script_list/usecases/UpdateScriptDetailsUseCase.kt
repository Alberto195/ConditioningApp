package com.example.conditioning511.domain.script_list.usecases

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.storage.UserScriptStorageDatabase
import com.google.gson.Gson
import javax.inject.Inject

class UpdateScriptDetailsUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards UserScriptStorageDatabase
) {
    suspend fun execute(scriptDetail: ScriptDetailsModel?, id: Int) {
        scriptDetail?.let {
            val json = Gson().toJson(it)
            repository.updateDetailedScript(json, id)
        }
    }
}