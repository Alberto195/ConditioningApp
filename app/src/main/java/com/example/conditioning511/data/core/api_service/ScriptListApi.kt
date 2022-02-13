package com.example.conditioning511.data.core.api_service

import com.example.conditioning511.data.core.models.RoomNameModel
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.models.ScriptGeneralInfoModel
import com.example.conditioning511.data.rooms.models.RoomListModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ScriptListApi {

    @GET("app/get_scripts")
    suspend fun getAllScripts(
        @Query("did") sensorId: Int?,
    ): Response<ScriptGeneralInfoModel>

    @GET("dev/script")
    suspend fun getScriptDetails(
        @Query("did") sensorId: Int?,
        @Query("sc_id") scriptId: Int?,
    ): Response<ScriptDetailsModel>

    @GET("app/datchik")
    suspend fun getAllRooms(
        @Query("did") sensorId: Int?,
    ): Response<RoomListModel>

    @GET("app/rm_config")
    suspend fun getRoomsNames(
        @Query("did") sensorId: Int?,
    ): Response<RoomNameModel>
}
