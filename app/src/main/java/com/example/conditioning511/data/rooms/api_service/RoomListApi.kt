package com.example.conditioning511.data.rooms.api_service

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.models.ScriptGeneralInfoModel
import com.example.conditioning511.data.rooms.models.RoomListModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RoomListApi {

    @GET("app/datchik")
    suspend fun getAllRooms(
        @Query("did") sensorId: Int?,
    ): Response<RoomListModel>

    companion object {
        operator fun invoke(): RoomListApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(RoomListApi::class.java)
        }

        private const val BASE_URL = "https://back.vc-app.ru/"
    }
}