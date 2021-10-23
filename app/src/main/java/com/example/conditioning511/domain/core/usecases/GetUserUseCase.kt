package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.domain.core.models.UserInitModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository

class GetUserUseCase(
    private val repository: ScriptListRepository
) {

    suspend fun execute(): UserInitModel {
        return repository.getUser()
    }

}