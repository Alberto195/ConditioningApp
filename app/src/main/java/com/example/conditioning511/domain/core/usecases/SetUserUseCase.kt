package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.domain.core.models.UserInitModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository

class SetUserUseCase(
    private val repository: ScriptListRepository
) {

    suspend fun execute(init: UserInitModel) {
        return repository.setUser(init)
    }

}