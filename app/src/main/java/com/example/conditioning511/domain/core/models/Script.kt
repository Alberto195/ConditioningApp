package com.example.conditioning511.domain.core.models

import java.io.Serializable

data class Script(
    val isCurrent: Boolean,
    val name: String,
    val scId: String,
)