package com.example.conditioning511.domain.script_list.usecases

import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.script_list.repositories.ScriptListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {
    suspend fun execute(): Flow<List<Room>> {
        return repository.getRooms()
    }
}