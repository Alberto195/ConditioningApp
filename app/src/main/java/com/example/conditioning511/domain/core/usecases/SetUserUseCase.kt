package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.domain.core.models.UserInitModel
import com.example.conditioning511.domain.core.repositories.RoomDbRepository
import javax.inject.Inject

class SetUserUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards RoomDbRepository
) {

    suspend fun execute(init: UserInitModel) {
        return repository.setUser(init)
    }

}