package com.example.conditioning511.presentation.script_list.viewmodels

import androidx.lifecycle.ViewModel
import com.example.conditioning511.domain.script_list.models.DayGroup
import com.example.conditioning511.domain.script_list.models.RoomGroups
import com.example.conditioning511.domain.script_list.models.Setting
import com.example.conditioning511.domain.script_list.models.TestScript
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.ArrayList

class ScriptListViewModel : ViewModel() {
    private val _nameStateFlow = MutableStateFlow("")
    val nameStateFlow: StateFlow<String> = _nameStateFlow.asStateFlow()

    private val _settingsStateFlow = MutableStateFlow(
        Setting(null, null, null, null, null, null, null, null)
    )

    private val _dayGroupStateFlow = MutableStateFlow(
        DayGroup(days = null, settings = null)
    )

    private val _roomGroupsStateFlow =
        MutableStateFlow(
            RoomGroups(
                dayGroups = null,
                rIDs = null
            )
        )
    val roomGroupsStateFlow: StateFlow<RoomGroups> = _roomGroupsStateFlow.asStateFlow()

    fun onNameChanged(name: String) {
        _nameStateFlow.value = name
    }

    fun changeRids(rids: List<Int>) = _roomGroupsStateFlow.update {
        it.copy(rIDs = rids)
    }

    fun daysChanged(days: List<Int>) = _dayGroupStateFlow.update {
        it.copy(days = days.sorted())
    }

    fun timeChanged(time: String) = _settingsStateFlow.update {
        it.copy(time = time)
    }

    fun updateRoomGroup() {
        _dayGroupStateFlow.update {
            it.copy(settings = listOf(_settingsStateFlow.value))
        }
        _roomGroupsStateFlow.update {
            it.copy(dayGroups = listOf(_dayGroupStateFlow.value))
        }
    }

    fun getListOfScripts(): ArrayList<TestScript> {
        return arrayListOf(
            TestScript("0", "test", true),
            TestScript("1", "script", false),
            TestScript("2", "script", false),
            TestScript("3", "test", false),
            TestScript("4", "script", false),
            TestScript("5", "script", false),
            TestScript("6", "script", false),
            TestScript("7", "script", false),
        )
    }

    fun getRooms(): ArrayList<Room> {
        return arrayListOf(
            Room(0, "Кухня"),
            Room(1, "Гостиная"),
            Room(2, "Умывальня"),
            Room(3, "Большая комната"),
            Room(4, "Детская"),
        )
    }

    fun getScript(index: String): RoomGroups? {
        return null
    }

    data class Room(
        val id: Int,
        val name: String
    )
}
