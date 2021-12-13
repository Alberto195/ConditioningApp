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
                temp_value = 34,
                co2 = 89,
                hum = 29,
                people = 2
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 34,
                temp_value = 34,
                co2 = 89,
                hum = 29,
                people = 2
            ),
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
                temp_value = 34,
                co2 = 89,
                hum = 29,
                people = 2
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 34,
                temp_value = 34,
                co2 = 89,
                hum = 29,
                people = 2
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 34,
                temp_value = 34,
                co2 = 89,
                hum = 29,
                people = 2
            )
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