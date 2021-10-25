package com.example.conditioning511.data.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.conditioning511.domain.core.models.ScriptIdDetailsModel
import com.example.conditioning511.domain.core.models.SensorIdModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScriptWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val repository: ScriptListRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            withContext(Dispatchers.IO) {
                val scIdList = repository.getScriptGeneralInfo(
                    SensorIdModel("") // did
                )
                scIdList?.forEach {
                    val script = repository.getScriptDetails(
                        ScriptIdDetailsModel(
                            scId = it.scId,
                            sensorId = ""
                        )
                    )
                    val jsonScript = Gson().toJson(script)
                    repository.insertDetailedScript(jsonScript)
                }
            }
            Result.success()
        } catch (e: Error) {
            Result.failure()
        }

    }
}