package com.example.conditioning511.presentation.theme

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun colorText(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        textColor = Color(0xFF9DB8CC),
        cursorColor = Color(0xFF9DB8CC),
        leadingIconColor = Color(0xFF9DB8CC),
        trailingIconColor = Color(0xFF9DB8CC),
        backgroundColor = Color(0xFFF2F7F9),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
}