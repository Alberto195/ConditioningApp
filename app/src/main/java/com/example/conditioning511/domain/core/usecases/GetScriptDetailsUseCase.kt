package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.domain.core.models.ScriptIdDetailsModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository

class GetScriptDetailsUseCase(
    private val repository: ScriptListRepository
) {

    suspend fun execute(ids: ScriptIdDetailsModel?): ScriptDetailsModel? {
        return repository.getScriptDetails(ids)
    }

}