package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository
import javax.inject.Inject

class InsertDetailedScriptUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {

    suspend fun execute(script: String) {
        return repository.insertDetailedScript(script)
    }

}