package com.example.conditioning511.data.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.domain.core.models.ScriptIdDetailsModel
import com.example.conditioning511.domain.core.models.SensorIdModel
import com.example.conditioning511.domain.core.repositories.RoomDbRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScriptWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val repository: RoomDbRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            withContext(Dispatchers.IO) {
                val scripts = repository.getScriptGeneralInfo(
                    SensorIdModel(0) // did
                )
                scripts?.forEach {
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
            withContext(Dispatchers.IO) {
                val names = repository.getRoomNames(SensorIdModel(0))
                val sortedNames = names?.sortedBy { it.rid }
                repository.getRoomsList(
                    com.example.conditioning511.domain.rooms.models.SensorIdModel(0) //did
                )?.sortedBy { it.rId }
                    ?.let { rooms ->
                        rooms.forEachIndexed { i, room ->
                            room.name = sortedNames?.get(i)?.name
                        }
                        repository.insertRoom(rooms)
                    }
            }
            Result.success()
        } catch (e: Error) {
            Result.failure()
        }
    }
}