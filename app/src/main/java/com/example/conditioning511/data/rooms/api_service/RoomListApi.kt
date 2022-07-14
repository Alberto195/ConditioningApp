package com.example.conditioning511.data.rooms.api_service

import com.example.conditioning511.data.rooms.models.RoomListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RoomListApi {
    @GET("app/datchik")
    suspend fun getAllRooms(
        @Query("did") sensorId: Int?,
    ): Response<RoomListModel>
}