package com.example.conditioning511.data.core.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ScriptGeneralInfoModel(
    @SerializedName("scriptList")
    val scriptList: List<Script>
) {
    data class Script(
        @SerializedName("is_current")
        val isCurrent: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("sc_id")
        val scId: String
    )
}