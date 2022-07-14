package com.example.conditioning511.domain.core.repositories

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.domain.core.models.Script
import com.example.conditioning511.domain.core.models.ScriptIdDetailsModel
import com.example.conditioning511.domain.core.models.SensorIdModel
import com.example.conditioning511.domain.core.models.UserInitModel
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.rooms.models.RoomName

interface RoomDbRepository {
    suspend fun setScriptGeneralInfo(scripts: List<Script>?)

    suspend fun getScriptGeneralInfo(did: SensorIdModel): List<Script>?

    suspend fun getScriptDetails(ids: ScriptIdDetailsModel?): ScriptDetailsModel?

    suspend fun insertDetailedScript(script: String?, id: Int)

    suspend fun getUser() : UserInitModel

    suspend fun setUser(init: UserInitModel)

    suspend fun initScriptWorker(): LiveData<WorkInfo>

    suspend fun getRoomsList(did: com.example.conditioning511.domain.rooms.models.SensorIdModel?): List<Room>?

    suspend fun insertRoom(room: List<Room>)

    suspend fun getRoomNames(did: SensorIdModel): List<RoomName>?
}