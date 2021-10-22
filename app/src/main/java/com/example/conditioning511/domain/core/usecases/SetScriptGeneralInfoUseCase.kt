package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.domain.core.models.Script
import com.example.conditioning511.domain.core.repositories.ScriptListRepository

class SetScriptGeneralInfoUseCase(
    private val repository: ScriptListRepository
) {

    suspend fun execute(scripts: List<Script>?) {
        return repository.setScriptGeneralInfo(scripts)
    }

}