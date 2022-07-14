package com.example.conditioning511.presentation.script_list.viewmodels

import androidx.lifecycle.ViewModel
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.script_list.models.Script
import com.example.conditioning511.domain.script_list.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.lang.Thread.sleep
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ScriptListViewModel @Inject constructor(
    private val getScriptsUseCase: GetScriptsUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val getRoomGroupsUseCase: GetRoomGroupsUseCase,
    private val saveScriptDetailsUseCase: SaveScriptDetailsUseCase,
    private val insertGeneralScriptInfoUseCase: InsertGeneralScriptInfoUseCase,
    private val updateScriptDetailsUseCase: UpdateScriptDetailsUseCase,
    private val clearDatabaseUseCase: ClearDatabaseUseCase,
    // TODO save new room script at every stage
    // TODO delete certain room/date/setting group
) : ViewModel() {

    private val _nameStateFlow = MutableStateFlow("")
    val nameStateFlow: StateFlow<String> = _nameStateFlow.asStateFlow()

    private val _timeStateFlow = MutableStateFlow("")
    val timeStateFlow: StateFlow<String> = _timeStateFlow.asStateFlow()

    private val _mustUseStateFlow = MutableStateFlow(listOf<Any>())
    val mustUseStateFlow: StateFlow<List<Any>> = _mustUseStateFlow.asStateFlow()

    private val _dontUseStateFlow = MutableStateFlow(listOf<Any>())
    val dontUseStateFlow: StateFlow<List<Any>> = _dontUseStateFlow.asStateFlow()

    val indexRoomGroupStateFlow = MutableStateFlow(0)
    val indexDayGroupStateFlow = MutableStateFlow(0)
    val indexSettingGroupStateFlow = MutableStateFlow(0)
    val indexArgumentsStateFlow = MutableStateFlow(0)

    private val _scripts = MutableStateFlow<List<Script>?>(null)
    val scripts: StateFlow<List<Script>?> = _scripts.asStateFlow()

    private val _roomsStateFlow = MutableStateFlow<List<Room>?>(null)
    val roomsStateFlow: StateFlow<List<Room>?> = _roomsStateFlow.asStateFlow()

    private val _roomGroupListStateFlow = MutableStateFlow(ScriptDetailList(null))
    val roomGroupListStateFlow: StateFlow<ScriptDetailList> =
        _roomGroupListStateFlow.asStateFlow()

    private val _roomGroupStateFlow = MutableStateFlow(ScriptDetailsRoomGroupList(null))
    val roomGroupStateFlow: StateFlow<ScriptDetailsRoomGroupList> =
        _roomGroupStateFlow.asStateFlow()

    private val _dayGroupStateFlow =
        MutableStateFlow(ScriptDetailsRoomGroupDayGroupList(null))
    val dayGroupStateFlow: StateFlow<ScriptDetailsRoomGroupDayGroupList> =
        _dayGroupStateFlow.asStateFlow()

    private val _settingGroupStateFlow =
        MutableStateFlow(ScriptDetailsRoomGroupDayGroupSettingList(null))
    val settingGroupStateFlow: StateFlow<ScriptDetailsRoomGroupDayGroupSettingList> =
        _settingGroupStateFlow.asStateFlow()

    fun onNewScriptDetail() {
        CoroutineScope(Dispatchers.IO).launch {
            saveScriptDetailsUseCase.execute(
                ScriptDetailsModel(
                    did = 0,
                    name = _nameStateFlow.value,
                    roomGroups = listOf()
                ),
                id = _roomGroupListStateFlow.value.list?.size ?: 0,
            )
            indexRoomGroupStateFlow.value = _roomGroupListStateFlow.value.list?.size ?: 0
            insertGeneralScript(_nameStateFlow.value, indexRoomGroupStateFlow.value)
        }
    }

    fun onNameChanged(name: String) {
        _nameStateFlow.value = name
    }

    fun onTimeChanged(time: String) {
        _timeStateFlow.value = time
    }

    fun changeRids(rids: List<Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            _roomGroupListStateFlow.value.let { scriptDetailsList ->
                scriptDetailsList.list?.forEachIndexed { i, it ->
                    if (it.name == _nameStateFlow.value) {
                        val roomGroups = mutableListOf<ScriptDetailsModel.RoomGroup>()
                        it.roomGroups.forEachIndexed { index, roomGroup ->
                            if (index != indexDayGroupStateFlow.value) roomGroups.add(roomGroup)
                            else {
                                roomGroups.add(
                                    ScriptDetailsModel.RoomGroup(
                                        dayGroups = listOf(),
                                        rIDs = rids,
                                    )
                                )
                            }
                        }
                        if (it.roomGroups.size == indexDayGroupStateFlow.value) roomGroups.add(
                            ScriptDetailsModel.RoomGroup(
                                dayGroups = listOf(),
                                rIDs = rids,
                            )
                        )
                        it.roomGroups = roomGroups
                        updateScriptDetailsUseCase.execute(
                            it,
                            id = i,
                        )
                    }
                }
            }
        }
    }

    fun daysChanged(days: List<Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            _roomGroupStateFlow.value.let {
                it.list?.forEach{ rG ->
                    if (rG.rIDs == it.list[indexDayGroupStateFlow.value].rIDs) {
                        val dayGroups = mutableListOf<ScriptDetailsModel.RoomGroup.DayGroup>()
                        rG.dayGroups.forEachIndexed { _, dayGroup ->
                            // проблема с индексом indexSettingGroupStateFlow
                            dayGroups.add(dayGroup)
                        }
                        dayGroups.add(
                            ScriptDetailsModel.RoomGroup.DayGroup(
                                days = days,
                                settings = listOf(),
                            )
                        )
                        if (dayGroups.size == indexSettingGroupStateFlow.value) dayGroups.add(
                            ScriptDetailsModel.RoomGroup.DayGroup(
                                days = days,
                                settings = listOf(),
                            )
                        )
                        _roomGroupListStateFlow.value.let { scriptDetailsList ->
                            scriptDetailsList.list?.forEachIndexed { i, it ->
                                if (it.name == _nameStateFlow.value) {
                                    val roomGroups = mutableListOf<ScriptDetailsModel.RoomGroup>()
                                    it.roomGroups.forEachIndexed { index, roomGroup ->
                                        if (index != indexDayGroupStateFlow.value) roomGroups.add(roomGroup)
                                        else {
                                            roomGroups.add(
                                                ScriptDetailsModel.RoomGroup(
                                                    dayGroups = dayGroups,
                                                    rIDs = rG.rIDs,
                                                )
                                            )
                                        }
                                    }
                                    if (it.roomGroups.size == indexDayGroupStateFlow.value) roomGroups.add(
                                        ScriptDetailsModel.RoomGroup(
                                            dayGroups = dayGroups,
                                            rIDs = rG.rIDs,
                                        )
                                    )
                                    it.roomGroups = roomGroups
                                    updateScriptDetailsUseCase.execute(
                                        it,
                                        id = i,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        _dayGroupStateFlow.update { dayGroups ->
            val list = mutableListOf(
                ScriptDetailsModel.RoomGroup.DayGroup(
                    days = days,
                    settings = listOf()
                )
            )
            dayGroups.list?.let { list.addAll(it) }
            ScriptDetailsRoomGroupDayGroupList(list.toList())
        }
    }

    fun settingsChanged(
        co2: Int,
        dontUse: List<Any>,
        hum: Int,
        mustUse: List<Any>,
        mute: Int,
        temp: Int,
        atHome: Int,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            _dayGroupStateFlow.value.let { dayGroupList ->
                dayGroupList.list?.forEach { dayGroup ->
                    if (dayGroup.days == dayGroupList.list[indexSettingGroupStateFlow.value].days) {
                        val settings =
                            mutableListOf<ScriptDetailsModel.RoomGroup.DayGroup.Setting>()
                        dayGroup.settings.forEachIndexed { i, item ->
                            if (i != indexArgumentsStateFlow.value) settings.add(item)
                            else settings.add(
                                ScriptDetailsModel.RoomGroup.DayGroup.Setting(
                                    atHome = atHome,
                                    time = _timeStateFlow.value,
                                    co2 = co2,
                                    dontUse = dontUse,
                                    hum = hum,
                                    mustUse = mustUse,
                                    mute = mute,
                                    temp = temp,
                                )
                            )
                        }
                        if (dayGroup.settings.size == indexArgumentsStateFlow.value) settings.add(
                            ScriptDetailsModel.RoomGroup.DayGroup.Setting(
                                atHome = atHome,
                                time = _timeStateFlow.value,
                                co2 = co2,
                                dontUse = dontUse,
                                hum = hum,
                                mustUse = mustUse,
                                mute = mute,
                                temp = temp,
                            )
                        )
                        _roomGroupStateFlow.value.let { roomGroupList ->
                            roomGroupList.list?.forEach { rG ->
                                if (rG.rIDs == roomGroupList.list[indexDayGroupStateFlow.value].rIDs) {
                                    val dayGroups = mutableListOf<ScriptDetailsModel.RoomGroup.DayGroup>()
                                    rG.dayGroups.forEachIndexed { index, dayGroup ->
                                        if (index != indexSettingGroupStateFlow.value) dayGroups.add(dayGroup)
                                        else dayGroups.add(
                                            ScriptDetailsModel.RoomGroup.DayGroup(
                                                days = dayGroup.days,
                                                settings = settings,
                                            )
                                        )
                                    }
                                    if (dayGroups.size == indexSettingGroupStateFlow.value) dayGroups.add(
                                        ScriptDetailsModel.RoomGroup.DayGroup(
                                            days = dayGroup.days,
                                            settings = settings,
                                        )
                                    )
                                    _roomGroupListStateFlow.value.let { scriptDetailsList ->
                                        scriptDetailsList.list?.forEachIndexed { i, it ->
                                            if (it.name == _nameStateFlow.value) {
                                                val roomGroups = mutableListOf<ScriptDetailsModel.RoomGroup>()
                                                it.roomGroups.forEachIndexed { index, roomGroup ->
                                                    if (index != indexDayGroupStateFlow.value) roomGroups.add(roomGroup)
                                                    else roomGroups.add(
                                                        ScriptDetailsModel.RoomGroup(
                                                            dayGroups = dayGroups,
                                                            rIDs = rG.rIDs,
                                                        )
                                                    )
                                                }
                                                if (it.roomGroups.size == indexDayGroupStateFlow.value) roomGroups.add(
                                                    ScriptDetailsModel.RoomGroup(
                                                        dayGroups = dayGroups,
                                                        rIDs = rG.rIDs,
                                                    )
                                                )
                                                it.roomGroups = roomGroups
                                                updateScriptDetailsUseCase.execute(
                                                    it,
                                                    id = i,
                                                )
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        _settingGroupStateFlow.update { setGroups ->
            val list = mutableListOf(
                ScriptDetailsModel.RoomGroup.DayGroup.Setting(
                    atHome = atHome,
                    time = _timeStateFlow.value,
                    co2 = co2,
                    dontUse = dontUse,
                    hum = hum,
                    mustUse = mustUse,
                    mute = mute,
                    temp = temp,
                )
            )
            setGroups.list?.let { list.addAll(it) }
            ScriptDetailsRoomGroupDayGroupSettingList(list.toList())
        }
    }

    fun clearSettingGroupStateFlow() {
        _settingGroupStateFlow.update { _ ->
            ScriptDetailsRoomGroupDayGroupSettingList(null)
        }
    }

    fun getListOfScripts() {
        CoroutineScope(Dispatchers.IO).launch {
            getScriptsUseCase.execute().collect {
                _scripts.value = it
            }
        }
    }

    fun getUpdate() {
        CoroutineScope(Dispatchers.IO).launch {
            updateScriptDetailsUseCase.execute(
                ScriptDetailsModel(
                    // Random().nextInt(10000 - 1) + 1,
                    100,
                    "update",
                    listOf(),
                ),
                id = 100,
            )
        }
    }

    private suspend fun insertGeneralScript(name: String, scId: Int) {
        insertGeneralScriptInfoUseCase.execute(
            ScriptGeneralInfoDbModel(
                isCurrent = false,
                name = name,
                scId = scId.toString(),
            )
        )
    }

    fun nameRoomIds(ids: List<Int>?): String {
        var name = ""
        _roomsStateFlow.value?.forEach { room ->
            ids?.forEach { id ->
                if (id == room.rId) name += room.name + " "
            }
        }
        return name
    }

    fun getRooms() {
        CoroutineScope(Dispatchers.IO).launch {
            getRoomsUseCase.execute().collect {
                _roomsStateFlow.value = it
            }
        }
    }

    fun getRoomGroupList() {
        CoroutineScope(Dispatchers.IO).launch {
            getRoomGroupsUseCase.execute().collect { scriptDetailsList ->
                _roomGroupListStateFlow.update { it.copy(list = scriptDetailsList) }
                _roomGroupStateFlow.update {
                    _roomGroupListStateFlow.value.let { list ->
                        if (list.list?.isNotEmpty() == true && list.list.size > indexRoomGroupStateFlow.value) it.copy(
                            list = list.list[indexRoomGroupStateFlow.value].roomGroups
                        )
                        else it
                    }
                }
                _dayGroupStateFlow.update {
                    _roomGroupStateFlow.value.let { roomGroup ->
                        if (roomGroup.list?.isNotEmpty() == true && roomGroup.list.size > indexDayGroupStateFlow.value) it.copy(
                            list = roomGroup.list[indexDayGroupStateFlow.value].dayGroups
                        )
                        else it
                    }
                }
                _settingGroupStateFlow.update {
                    _dayGroupStateFlow.value.let { dayGroup ->
                        if (dayGroup.list?.isNotEmpty() == true && dayGroup.list.size > indexSettingGroupStateFlow.value) it.copy(
                            list = dayGroup.list[indexSettingGroupStateFlow.value].settings
                        )
                        else it
                    }
                }
            }
        }
    }
}

data class ScriptDetailList(
    val list: List<ScriptDetailsModel>?
)

data class ScriptDetailsRoomGroupList(
    val list: List<ScriptDetailsModel.RoomGroup>?
)

data class ScriptDetailsRoomGroupDayGroupList(
    val list: List<ScriptDetailsModel.RoomGroup.DayGroup>?
)

data class ScriptDetailsRoomGroupDayGroupSettingList(
    val list: List<ScriptDetailsModel.RoomGroup.DayGroup.Setting>?
)