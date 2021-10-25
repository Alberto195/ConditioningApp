package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.domain.core.models.UserInitModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository
import javax.inject.Inject

class SetUserUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {

    suspend fun execute(init: UserInitModel) {
        return repository.setUser(init)
    }

}