package com.example.conditioning511.presentation.script_list.ui.add_script_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun GiveNameDialog() {
    Dialog(
        onDismissRequest = {

        },
    ) {
        Surface(modifier = Modifier.wrapContentSize()) {
            Column {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close dialog")
                Text(
                    text = "Новый сценарий",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Введите придуманное название",
                )
                TextField(value = "", onValueChange = {})
                Row {
                    Button(onClick = { /*TODO*/ }) {
                        Text("Отмена")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text("Продолжить")
                    }
                }
            }
        }
    }
}