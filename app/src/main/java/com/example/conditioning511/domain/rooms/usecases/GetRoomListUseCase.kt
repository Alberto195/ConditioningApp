package com.example.conditioning511.domain.rooms.usecases

import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.rooms.models.SensorIdModel
import com.example.conditioning511.domain.rooms.repositories.RoomRepository
import javax.inject.Inject

class GetRoomListUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards RoomRepository
) {

    suspend fun execute(id: SensorIdModel?): List<Room>? {
        return repository.getRoomsList(id)
    }

}