package com.example.conditioning511.data.core.storage.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "scripts_general_info", indices = [Index("id")])
data class ScriptGeneralInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val isCurrent: Boolean? = false,
    @ColumnInfo
    val name: String? = "",
    @ColumnInfo
    val scId: String? = ""
) : Serializable
