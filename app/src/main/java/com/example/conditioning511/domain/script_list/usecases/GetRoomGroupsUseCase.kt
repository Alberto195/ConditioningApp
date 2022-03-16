package com.example.conditioning511.domain.script_list.usecases

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.domain.script_list.repositories.ScriptListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomGroupsUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {
    suspend fun execute(): Flow<List<ScriptDetailsModel>> {
        return repository.getRoomGroups()
    }
}