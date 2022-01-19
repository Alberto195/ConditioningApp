package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.domain.core.models.Script
import com.example.conditioning511.domain.core.models.SensorIdModel
import com.example.conditioning511.domain.core.repositories.RoomDbRepository
import javax.inject.Inject

class GetScriptGeneralInfoIseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards RoomDbRepository
) {

    suspend fun execute(sensorId: SensorIdModel): List<Script>? {
        return repository.getScriptGeneralInfo(sensorId)
    }

}