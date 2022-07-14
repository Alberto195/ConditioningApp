package com.example.conditioning511.presentation.rooms.viewmodels

import androidx.lifecycle.ViewModel
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.rooms.usecases.InsertRoomsUseCase
import com.example.conditioning511.domain.script_list.usecases.GetRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsScreenViewModel @Inject constructor(
    private val insertRoomsUseCase: InsertRoomsUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,
) : ViewModel() {

    init {
/*
        CoroutineScope(Dispatchers.IO).launch {
            insertRoomsUseCase.execute(
                listOf(
                    Room(
                        rId = 0,
                        date = "14.03",
                        temp = 13,
                        temp_value = 15,
                        co2 = 44,
                        hum = null,
                        people = 4,
                        name = "кухня"
                    ),
                    Room(
                        rId = 1,
                        date = "13.03",
                        temp = 33,
                        temp_value = 15,
                        co2 = null,
                        hum = null,
                        people = 1,
                        name = "ванная"
                    ),
                    Room(
                        rId = 2,
                        date = "11.02",
                        temp = 37,
                        temp_value = 65,
                        co2 = 44,
                        hum = 19,
                        people = 6,
                        name = "спальня"
                    )
                )
            )
        }
*/
    }

    private val _roomsStateFlow = MutableStateFlow<List<Room>?>(null)
    val roomsStateFlow: StateFlow<List<Room>?> = _roomsStateFlow.asStateFlow()

    fun getRoomsList() {
        CoroutineScope(Dispatchers.IO).launch {
            getRoomsUseCase.execute().collect {
                _roomsStateFlow.value = it
            }
        }
    }
}
