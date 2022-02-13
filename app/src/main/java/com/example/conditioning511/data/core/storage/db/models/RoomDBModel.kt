package com.example.conditioning511.data.core.storage.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class RoomDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val rId: Int,
    @ColumnInfo
    val date: String?,
    @ColumnInfo
    val temp: Int?,
    @ColumnInfo
    val temp_value: Int?,
    @ColumnInfo
    val co2: Int?,
    @ColumnInfo
    val hum: Int?,
    @ColumnInfo
    val people: Int?,
    @ColumnInfo
    val name: String?,
)