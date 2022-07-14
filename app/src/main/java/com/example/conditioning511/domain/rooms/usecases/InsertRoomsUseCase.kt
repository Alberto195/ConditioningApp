package com.example.conditioning511.domain.rooms.usecases

import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.script_list.repositories.ScriptListRepository
import javax.inject.Inject

class InsertRoomsUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards ScriptListRepository
) {
    suspend fun execute(rooms: List<Room>) {
        return repository.insertRooms(rooms)
    }
}