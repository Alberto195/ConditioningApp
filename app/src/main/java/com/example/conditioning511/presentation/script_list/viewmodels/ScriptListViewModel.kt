package com.example.conditioning511.presentation.script_list.viewmodels

import androidx.lifecycle.ViewModel
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.script_list.models.RoomGroup
import com.example.conditioning511.domain.script_list.models.Script
import com.example.conditioning511.domain.script_list.models.Setting
import com.example.conditioning511.domain.script_list.usecases.GetRoomGroupsUseCase
import com.example.conditioning511.domain.script_list.usecases.GetRoomsUseCase
import com.example.conditioning511.domain.script_list.usecases.GetScriptsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScriptListViewModel @Inject constructor(
    private val getScriptsUseCase: GetScriptsUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val getRoomGroupsUseCase: GetRoomGroupsUseCase
    // TODO save new room script at every stage
    // TODO delete certain room/date/setting group
) : ViewModel() {

    private val _nameStateFlow = MutableStateFlow("")
    val nameStateFlow: StateFlow<String> = _nameStateFlow.asStateFlow()

    val indexRoomGroupStateFlow = MutableStateFlow(0)
    val indexDayGroupStateFlow = MutableStateFlow(0)
    val indexSettingGroupStateFlow = MutableStateFlow(0)

    private val _scripts = MutableStateFlow<List<Script>?>(null)
    val scripts: StateFlow<List<Script>?> = _scripts.asStateFlow()

    private val _roomsStateFlow = MutableStateFlow<List<Room>?>(null)
    val roomsStateFlow: StateFlow<List<Room>?> = _roomsStateFlow.asStateFlow()

    private val _settingsStateFlow = MutableStateFlow(
        Setting(null, null, null, null, null, null, null, null)
    )

    private val _roomGroupListStateFlow = MutableStateFlow<List<ScriptDetailsModel>?>(null)
    val roomGroupListStateFlow: StateFlow<List<ScriptDetailsModel>?> =
        _roomGroupListStateFlow.asStateFlow()

    private val _roomGroupStateFlow = MutableStateFlow<List<ScriptDetailsModel.RoomGroup>?>(null)
    val roomGroupStateFlow: StateFlow<List<ScriptDetailsModel.RoomGroup>?> =
        _roomGroupStateFlow.asStateFlow()

    private val _dayGroupStateFlow =
        MutableStateFlow<List<ScriptDetailsModel.RoomGroup.DayGroup>?>(null)
    val dayGroupStateFlow: StateFlow<List<ScriptDetailsModel.RoomGroup.DayGroup>?> =
        _dayGroupStateFlow.asStateFlow()

    private val _settingGroupStateFlow =
        MutableStateFlow<List<ScriptDetailsModel.RoomGroup.DayGroup.Setting>?>(null)
    val settingGroupStateFlow: StateFlow<List<ScriptDetailsModel.RoomGroup.DayGroup.Setting>?> =
        _settingGroupStateFlow.asStateFlow()


    fun onNameChanged(name: String) {
        _nameStateFlow.value = name
    }

    fun changeRids(rids: List<Int>) = _roomGroupStateFlow.update { roomGroups ->
        roomGroups?.mapIndexed { i, it ->
            if (indexRoomGroupStateFlow.value == i) it.copy(rIDs = rids) else it
        }
    }

    fun daysChanged(days: List<Int>) = _dayGroupStateFlow.update { dayGroups ->
        val list = mutableListOf(
            ScriptDetailsModel.RoomGroup.DayGroup(
                days = days,
                settings = listOf()
            )
        )
        if (dayGroups != null) {
            list.addAll(dayGroups)
        }
        list.toList()
    }

    fun timeChanged(time: String) = _settingsStateFlow.update {
        it.copy(time = time)
    }

//    fun updateDayGroup() {
//        _dayGroupStateFlow?.update {
//            it.copy(settings = listOf(_settingsStateFlow.value))
//        }
//    }
//
//    fun updateRoomGroup() {
//        _roomGroupStateFlow?.update {
//            it.copy(dayGroups = listOf(_dayGroupStateFlow.value))
//        }
//    }

    fun getListOfScripts() {
        CoroutineScope(Dispatchers.IO).launch {
            getScriptsUseCase.execute().collect {
                _scripts.value = it
            }
        }
    }

    fun getListOfScriptsTest() {
        CoroutineScope(Dispatchers.IO).launch {
            _scripts.value = arrayListOf(
                Script(scId = "0", name = "test", isCurrent = true),
                Script(scId = "1", name = "script", isCurrent = false),
                Script(scId = "2", name = "script", isCurrent = false),
                Script(scId = "3", name = "test", isCurrent = false),
                Script(scId = "4", name = "script", isCurrent = false),
                Script(scId = "5", name = "test", isCurrent = false),
            )
        }
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
            getRoomGroupsUseCase.execute().collect {
                _roomGroupListStateFlow.update { it }
                _roomGroupListStateFlow.value?.let { list ->
                    _roomGroupStateFlow.update { list[indexRoomGroupStateFlow.value].roomGroups }
                }
                _roomGroupStateFlow.value?.let { roomGroup ->
                    _dayGroupStateFlow.update { roomGroup[indexDayGroupStateFlow.value].dayGroups }
                }
                _dayGroupStateFlow.value?.let { dayGroup ->
                    _settingGroupStateFlow.update { dayGroup[indexSettingGroupStateFlow.value].settings }
                }
            }
        }
    }

    fun getRoomGroupListTest() {
        CoroutineScope(Dispatchers.IO).launch {
            val test = listOf(
                ScriptDetailsModel(
                    0, "asd", listOf(
                        ScriptDetailsModel.RoomGroup(
                            dayGroups = listOf(
                                ScriptDetailsModel.RoomGroup.DayGroup(
                                    days = listOf(1, 2, 3),
                                    settings = listOf(
                                        ScriptDetailsModel.RoomGroup.DayGroup.Setting(
                                            atHome = 0,
                                            co2 = 100,
                                            dontUse = listOf(),
                                            hum = 400,
                                            mustUse = listOf(),
                                            mute = 1,
                                            temp = 23,
                                            time = "14:00"
                                        ),
                                    )
                                ),
                                ScriptDetailsModel.RoomGroup.DayGroup(
                                    days = listOf(4, 5, 6),
                                    settings = listOf()
                                )
                            ),
                            rIDs = listOf(10, 20, 30)
                        ),
                        ScriptDetailsModel.RoomGroup(
                            dayGroups = listOf(),
                            rIDs = listOf(40, 50, 60)
                        )
                    )
                )
            )

            getRoomGroupsUseCase.execute().collect { _ ->
                _roomGroupListStateFlow.update {
                    test
                }
                _roomGroupListStateFlow.value?.let { _ ->
                    _roomGroupStateFlow.update {
                        test[indexRoomGroupStateFlow.value].roomGroups
                    }
                }
                _roomGroupStateFlow.value?.let { _ ->
                    _dayGroupStateFlow.update {
                        test[indexRoomGroupStateFlow.value].roomGroups[indexDayGroupStateFlow.value].dayGroups
                    }
                }
                _dayGroupStateFlow.value?.let { _ ->
                    _settingGroupStateFlow.update { test[indexRoomGroupStateFlow.value].roomGroups[indexDayGroupStateFlow.value].dayGroups[indexSettingGroupStateFlow.value].settings }
                }
            }
        }
    }

    fun getScript(index: String): RoomGroup? {
        return null
    }
}
