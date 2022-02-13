package com.example.conditioning511.data.script_list.storage.db

import com.example.conditioning511.data.core.storage.db.models.RoomDBModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.data.rooms.models.Source
import com.example.conditioning511.data.script_list.storage.ScriptStorageDatabase
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScriptStorageDatabaseImpl @Inject constructor(
    private val roomDao: RoomDao
) : ScriptStorageDatabase {
    override suspend fun getScriptsInfo(): Flow<List<ScriptGeneralInfoDbModel>> {
        return roomDao.getScripts()
    }

    override suspend fun getRooms(): Flow<List<Room>> {
        return roomDao.getRooms().map {
            it.mapToRoomList()
        }
    }
}

private fun List<RoomDBModel>.mapToRoomList(): List<Room> {
    return this.map {
        Room(
            rId = it.rId,
            date = it.date,
            temp = it.temp,
            temp_value = it.temp_value,
            co2 = it.co2,
            hum = it.hum,
            people = it.people,
            name = it.name
        )
    }
}
