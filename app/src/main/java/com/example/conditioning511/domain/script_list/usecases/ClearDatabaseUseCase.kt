package com.example.conditioning511.domain.script_list.usecases

import com.example.conditioning511.domain.script_list.repositories.ScriptListRepository
import javax.inject.Inject

class ClearDatabaseUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {
    suspend fun execute() {
        repository.clearDatabase()
    }
}