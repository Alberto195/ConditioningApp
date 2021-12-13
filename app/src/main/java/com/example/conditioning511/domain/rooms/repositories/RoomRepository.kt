package com.example.conditioning511.domain.rooms.repositories

import com.example.conditioning511.domain.rooms.models.SensorIdModel
import com.example.conditioning511.domain.rooms.models.Room

interface RoomRepository {
    suspend fun getRoomsList(did: SensorIdModel?): List<Room>?
}