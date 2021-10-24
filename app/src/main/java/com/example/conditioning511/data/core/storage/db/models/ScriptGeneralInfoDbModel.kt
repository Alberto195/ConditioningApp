package com.example.conditioning511.data.core.storage.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "scripts_general_info")
data class ScriptGeneralInfoDbModel(
    @ColumnInfo
    val isCurrent: Boolean? = false,
    @ColumnInfo
    val name: String? = "",
    @PrimaryKey
    val scId: String = "",
)
