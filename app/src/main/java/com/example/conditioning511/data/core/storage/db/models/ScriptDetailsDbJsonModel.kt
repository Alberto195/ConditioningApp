package com.example.conditioning511.data.core.storage.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// TODO table for roomGroups
@Entity(tableName = "script_details")
data class ScriptDetailsDbJsonModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val script: String
)