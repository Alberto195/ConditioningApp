package com.example.conditioning511.presentation.script_list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.script_list.models.DayGroup
import com.example.conditioning511.domain.script_list.models.RoomGroups
import com.example.conditioning511.domain.script_list.models.Script
import com.example.conditioning511.domain.script_list.models.Setting
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
) : ViewModel() {

    private val _nameStateFlow = MutableStateFlow("")
    val nameStateFlow: StateFlow<String> = _nameStateFlow.asStateFlow()

    private val _scripts = MutableStateFlow<List<Script>?>(null)
    val scripts: StateFlow<List<Script>?> = _scripts.asStateFlow()

    private val _roomsStateFlow = MutableStateFlow<List<Room>?>(null)
    val roomsStateFlow: StateFlow<List<Room>?> = _roomsStateFlow.asStateFlow()

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

    fun getListOfScripts() {
        CoroutineScope(Dispatchers.IO).launch {
            getScriptsUseCase.execute().collect {
                _scripts.value = it
            }
        }
    }

    fun getListOfScriptsReal(): List<Script> {
        return arrayListOf(
            Script(scId = "0", name = "test", isCurrent = true),
            Script(scId = "1", name = "script", isCurrent = false),
            Script(scId = "2", name = "script", isCurrent = false),
            Script(scId = "3", name = "test", isCurrent = false),
            Script(scId = "4", name = "script", isCurrent = false),
            Script(scId = "5", name = "test", isCurrent = false),
        )
    }

    fun getRooms() {
        CoroutineScope(Dispatchers.IO).launch {
            getRoomsUseCase.execute().collect {
                _roomsStateFlow.value = it
            }
        }
    }

    fun getScript(index: String): RoomGroups? {
        return null
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ScriptListViewModel as T
            }
        }
    }
}
