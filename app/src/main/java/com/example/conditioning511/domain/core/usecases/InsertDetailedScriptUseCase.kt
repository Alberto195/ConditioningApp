package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.domain.core.repositories.RoomDbRepository
import javax.inject.Inject

class InsertDetailedScriptUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards RoomDbRepository
) {

    suspend fun execute(script: String, id: Int) {
        return repository.insertDetailedScript(script, id)
    }

}