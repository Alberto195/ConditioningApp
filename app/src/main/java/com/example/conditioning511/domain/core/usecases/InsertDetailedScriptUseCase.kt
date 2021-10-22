package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository

class InsertDetailedScriptUseCase(
    private val repository: ScriptListRepository
) {

    suspend fun execute(script: ScriptDetailsModel) {
        return repository.insertDetailedScript(script)
    }

}