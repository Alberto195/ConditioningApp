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
                people = 3,
                name = "Кухня"
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 34,
                temp_value = null,
                co2 = 89,
                hum = 29,
                people = 2,
                name = "Название комнаты"
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 34,
                temp_value = null,
                co2 = null,
                hum = 29,
                people = 2,
                name = "Название комнаты"
            ),
            Room(
                rId = 1,
                date = "Кухня",
                temp = 15,
                temp_value = 34,
                co2 = 89,
                hum = null,
                people = 3,
                name = "Название комнаты"
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 11,
                temp_value = null,
                co2 = 89,
                hum = 29,
                people = 2,
                name = "Название комнаты"
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 12,
                temp_value = null,
                co2 = null,
                hum = null,
                people = 2,
                name = "Название комнаты"
            ),
            Room(
                rId = 5,
                date = "Название комнаты",
                temp = 34,
                temp_value = 34,
                co2 = 89,
                hum = null,
                people = null,
                name = "Название комнаты"
            ),
            Room(
                rId = 5,
                date = "Название комнаты большое дааааа",
                temp = 11,
                temp_value = null,
                co2 = 89,
                hum = 29,
                people = 2,
                name = "Название комнаты большое дааааа"
            ),
            Room(
                rId = 5,
                date = "Название комнаты большое дааааа",
                temp = 11,
                temp_value = null,
                co2 = 89,
                hum = 29,
                people = 2,
                name = "Название комнаты большое дааааа"
            ),
        )
    }
}
