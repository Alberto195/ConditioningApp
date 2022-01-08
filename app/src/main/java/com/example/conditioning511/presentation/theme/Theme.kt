package com.example.conditioning511.presentation.theme

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun colorText(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        cursorColor = Color.White,
        leadingIconColor = Color.Black,
        trailingIconColor = Color.Black,
        backgroundColor = Color.LightGray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
}