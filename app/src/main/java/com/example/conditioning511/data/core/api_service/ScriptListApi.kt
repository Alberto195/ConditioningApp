package com.example.conditioning511.data.core.api_service

import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.models.ScriptGeneralInfoModel
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

    companion object {
        operator fun invoke(): ScriptListApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ScriptListApi::class.java)
        }

        private const val BASE_URL = "https://back.vc-app.ru/"
    }
}