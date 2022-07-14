package com.example.conditioning511.domain.script_list.usecases

import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.domain.script_list.repositories.ScriptListRepository
import javax.inject.Inject

class InsertGeneralScriptInfoUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {
    suspend fun execute(script: ScriptGeneralInfoDbModel) {
        repository.insertScriptGeneral(script)
    }
}