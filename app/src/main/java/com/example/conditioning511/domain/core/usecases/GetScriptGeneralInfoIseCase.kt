package com.example.conditioning511.domain.core.usecases

import com.example.conditioning511.domain.core.models.Script
import com.example.conditioning511.domain.core.models.SensorIdModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository

class GetScriptGeneralInfoIseCase(
    private val repository: ScriptListRepository
) {

    suspend fun execute(sensorId: SensorIdModel): List<Script>? {
        return repository.getScriptGeneralInfo(sensorId)
    }

}