package com.example.conditioning511.presentation.rooms.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conditioning511.domain.rooms.models.Room

class RoomsScreenViewModel(): ViewModel() {

    fun getRoomsList(): List<Room> {
        return listOf(
            Room(
                rId = 1,
                date = "Кухня",
                temp = 24,
                temp_value = 34,
                co2 = 89,
                hum = 29,
                people = 3
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 34,
                temp_value = null,
                co2 = 89,
                hum = 29,
                people = 2
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 34,
                temp_value = null,
                co2 = null,
                hum = 29,
                people = 2
            ),
            Room(
                rId = 1,
                date = "Кухня",
                temp = 15,
                temp_value = 34,
                co2 = 89,
                hum = null,
                people = 3
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 11,
                temp_value = null,
                co2 = 89,
                hum = 29,
                people = 2
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 12,
                temp_value = null,
                co2 = null,
                hum = null,
                people = 2
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 34,
                temp_value = 34,
                co2 = 89,
                hum = null,
                people = null
            ),
            Room(
                rId = 5,
                date = "Название комнаты большое дааааа",
                temp = 11,
                temp_value = null,
                co2 = 89,
                hum = 29,
                people = 2
            ),
            Room(
                rId = 5,
                date = "Название комнаты большое дааааа",
                temp = 11,
                temp_value = null,
                co2 = 89,
                hum = 29,
                people = 2
            ),
        )
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RoomsScreenViewModel() as T
            }
        }
    }
}