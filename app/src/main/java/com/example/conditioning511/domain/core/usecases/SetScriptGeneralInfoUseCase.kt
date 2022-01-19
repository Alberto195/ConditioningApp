package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.domain.core.models.Script
import com.example.conditioning511.domain.core.repositories.RoomDbRepository
import javax.inject.Inject

class SetScriptGeneralInfoUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards RoomDbRepository
) {

    suspend fun execute(scripts: List<Script>?) {
        return repository.setScriptGeneralInfo(scripts)
    }

}