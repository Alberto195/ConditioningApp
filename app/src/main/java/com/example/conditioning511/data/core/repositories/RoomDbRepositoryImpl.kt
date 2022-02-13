package com.example.conditioning511.data.core.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.conditioning511.data.core.api_service.ScriptListApi
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.models.ScriptGeneralInfoModel
import com.example.conditioning511.data.core.storage.UserScriptStorageDatabase
import com.example.conditioning511.data.core.storage.UserStorageSharedPreference
import com.example.conditioning511.data.core.storage.db.models.RoomDBModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.data.core.workers.ScriptWorker
import com.example.conditioning511.data.rooms.models.Source
import com.example.conditioning511.domain.core.models.Script
import com.example.conditioning511.domain.core.models.ScriptIdDetailsModel
import com.example.conditioning511.domain.core.models.SensorIdModel
import com.example.conditioning511.domain.core.models.UserInitModel
import com.example.conditioning511.domain.core.repositories.RoomDbRepository
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.rooms.models.RoomName
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RoomDbRepositoryImpl @Inject constructor(
    private val api: ScriptListApi,
    private val userScriptStorageDatabase: UserScriptStorageDatabase,
    private val userStorageSharedPreference: UserStorageSharedPreference,
    private val context: Context
) : @JvmSuppressWildcards RoomDbRepository {

    override suspend fun getRoomsList(did: com.example.conditioning511.domain.rooms.models.SensorIdModel?): List<Room>? {
        val scripts = api.getAllRooms(did?.did)
        return scripts.body()?.sources.mapToRoomList()
    }

    override suspend fun insertRoom(room: List<Room>) {
        userScriptStorageDatabase.insertRoom(
            room.mapToDBRoomModelList()
        )
    }

    override suspend fun getRoomNames(did: SensorIdModel): List<RoomName>? {
        val roomNames = api.getRoomsNames(did.did)
        return roomNames.body()?.rooms?.map {
            RoomName(
                name = it.name,
                rid = it.rid
            )
        }
    }

    override suspend fun setScriptGeneralInfo(scripts: List<Script>?) {
        userScriptStorageDatabase.setScriptGeneralInfo(
            scripts?.mapToStorageDb()
        )
    }

    override suspend fun getScriptGeneralInfo(did: SensorIdModel): List<Script>? {
        val scripts = api.getAllScripts(did.did)
        return scripts.body()?.mapToScript()
    }

    override suspend fun getScriptDetails(ids: ScriptIdDetailsModel?): ScriptDetailsModel? {
        val scriptDetails = api.getScriptDetails(ids?.sensorId?.toInt(), ids?.scId?.toInt())

        return scriptDetails.body()
    }

    override suspend fun insertDetailedScript(script: String?) {
        userScriptStorageDatabase.insertDetailedScript(script)
    }

    override suspend fun getUser(): UserInitModel {
        return UserInitModel(
            init = userStorageSharedPreference.getUser()
        )
    }

    override suspend fun setUser(init: UserInitModel) {
        userStorageSharedPreference.setUser(init.init)
    }

    override suspend fun initScriptWorker(): LiveData<WorkInfo> {
        val work = PeriodicWorkRequestBuilder<ScriptWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(context).enqueue(work)
        return WorkManager.getInstance(context).getWorkInfoByIdLiveData(work.id)
    }


    private fun ScriptGeneralInfoModel.mapToScript(): List<Script> {
        return this.scriptList.map {
            Script(
                isCurrent = it.isCurrent,
                name = it.name,
                scId = it.scId
            )
        }
    }

    private fun List<Script>.mapToStorageDb(): List<ScriptGeneralInfoDbModel> {
        return this.map {
            ScriptGeneralInfoDbModel(
                isCurrent = it.isCurrent,
                name = it.name,
                scId = it.scId
            )
        }
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
                name = it.name,
            )
        }
    }

    private fun List<Room>.mapToDBRoomModelList(): List<RoomDBModel> {
        return this.map {
            RoomDBModel(
                rId = it.rId,
                date = it.date,
                temp = it.temp,
                temp_value = it.temp_value,
                co2 = it.co2,
                hum = it.hum,
                people = it.people,
                name = it.name,
            )
        }
    }
}
