package com.example.conditioning511.data.rooms.repositories

import com.example.conditioning511.data.rooms.api_service.RoomListApi
import com.example.conditioning511.data.rooms.models.Source
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.rooms.models.SensorIdModel
import com.example.conditioning511.domain.rooms.repositories.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val api: RoomListApi,
): RoomRepository {

    override suspend fun getRoomsList(did: SensorIdModel?): List<Room>? {
        val scripts = api.getAllRooms(did?.did)
        return scripts.body()?.sources.mapToRoomList()
    }

    private fun List<Source>?.mapToRoomList(): List<Room>? {
        return this?.map {
            Room(
                rId = it.rid,
                date = it.dt,
                temp = it.temp,
                temp_value = it.temp_valve,
                co2 = it.co2,
                hum = it.hum,
                people = it.people,
            )
        }
    }
}
