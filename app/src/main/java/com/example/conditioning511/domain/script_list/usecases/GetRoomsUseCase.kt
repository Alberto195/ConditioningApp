package com.example.conditioning511.domain.script_list.usecases

import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.domain.script_list.repositories.ScriptListRepository
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {
    suspend fun execute(): Flow<List<ScriptListViewModel.Room>> {
        return repository.getRooms()
    }
}