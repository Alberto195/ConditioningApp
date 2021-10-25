package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.domain.core.models.ScriptIdDetailsModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository
import javax.inject.Inject

class GetScriptDetailsUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {

    suspend fun execute(ids: ScriptIdDetailsModel?): ScriptDetailsModel? {
        return repository.getScriptDetails(ids)
    }

}