package com.example.conditioning511.domain.script_list.usecases

import androidx.lifecycle.LiveData
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.domain.script_list.models.Script
import com.example.conditioning511.domain.script_list.repositories.ScriptListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetScriptsUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {
    suspend fun execute(): Flow<List<Script>> {
        return repository.getScripts()
    }
}