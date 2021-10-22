package com.example.conditioning511.data.core.repositories

import com.example.conditioning511.data.core.api_service.ScriptListApi
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.models.ScriptGeneralInfoModel
import com.example.conditioning511.data.core.storage.UserScriptStorageDatabase
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.domain.core.models.Script
import com.example.conditioning511.domain.core.models.ScriptIdDetailsModel
import com.example.conditioning511.domain.core.models.SensorIdModel
import com.example.conditioning511.domain.core.repositories.ScriptListRepository
import kotlin.reflect.full.memberProperties

class ScriptListRepositoryImpl(
    private val api: ScriptListApi,
    private val userScriptStorageDatabase: UserScriptStorageDatabase
) : ScriptListRepository {

    override suspend fun setScriptGeneralInfo(scripts: List<Script>?) {
        userScriptStorageDatabase.setScriptGeneralInfo(
            scripts?.mapToStorageDb()
        )
    }

    override suspend fun getScriptGeneralInfo(did: SensorIdModel): List<Script>? {
        val scripts = api.getAllScripts(did.did?.toInt())
        return scripts.body()?.mapToScript()
    }

    override suspend fun getScriptDetails(ids: ScriptIdDetailsModel?): ScriptDetailsModel? {
        val scriptDetails = api.getScriptDetails(ids?.sensorId?.toInt(), ids?.sc_id?.toInt())

        return scriptDetails.body()
    }

    override suspend fun insertDetailedScript(script: ScriptDetailsModel) {
        userScriptStorageDatabase.insertDetailedScript(
            script.mapToStorage()
        )
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

    private fun ScriptDetailsModel.mapToStorage() = with(::ScriptDetailsDbModel) {
        val propertiesByName = ScriptDetailsModel::class.memberProperties.associateBy { it.name }

        callBy(args = parameters.associateWith { parameter ->
            propertiesByName[parameter.name]?.get(this@mapToStorage)
        })
    }

}