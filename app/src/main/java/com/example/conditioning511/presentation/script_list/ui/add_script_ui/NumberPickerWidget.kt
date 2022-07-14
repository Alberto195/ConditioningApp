package com.example.conditioning511.presentation.script_list.ui.add_script_ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.conditioning511.R
import com.example.conditioning511.presentation.script_list.ui.time_picker.*

@Preview
@Composable
fun MainActivityUI() {

    val scrollState = rememberScrollState()

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    NumberPickerWidget()
                    HoursNumberPicker1()
                    HoursNumberPicker2()
                    HoursNumberPicker3()
                    HoursNumberPicker4()
                    DoublesPicker()
                    FruitPicker()
                    IntRangePicker()
                }
            }
        }
    }
}

@Composable
private fun NumberPickerWidget() {
    var state by remember { mutableStateOf(0) }
    NumberPicker(
        value = state,
        range = 0..10,
        onValueChange = {
            state = it
        }
    )
}

@Composable
private fun HoursNumberPicker1() {
    var state by remember { mutableStateOf<Hours>(FullHours(12, 43)) }
    com.example.conditioning511.presentation.script_list.ui.time_picker.HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        dividersColor = Color.White,
        value = state,
        onValueChange = {
            state = it
        },
        hoursDivider = {
            Text(
                modifier = Modifier.size(24.dp),
                textAlign = TextAlign.Center,
                text = ":"
            )
        }
    )
}

@Composable
private fun HoursNumberPicker2() {
    var state by remember { mutableStateOf<Hours>(AMPMHours(9, 43, AMPMHours.DayTime.PM)) }
    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        dividersColor = MaterialTheme.colors.secondary,
        value = state,
        onValueChange = {
            state = it
        },
        hoursDivider = {
            Text(
                modifier = Modifier.size(24.dp),
                textAlign = TextAlign.Center,
                text = ":"
            )
        },
        minutesDivider = {
            Spacer(
                modifier = Modifier.size(24.dp),
            )
        }
    )
}

@Composable
private fun HoursNumberPicker3() {
    var state by remember { mutableStateOf<Hours>(FullHours(9, 20)) }

    com.example.conditioning511.presentation.script_list.ui.time_picker.HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        value = state,
        onValueChange = {
            state = it
        },
        minutesRange = IntProgression.fromClosedRange(0, 50, 10),
        hoursDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "h"
            )
        },
        minutesDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "m"
            )
        }
    )
}

@Composable
private fun HoursNumberPicker4() {
    var state by remember { mutableStateOf<Hours>(FullHours(11, 36)) }
    com.example.conditioning511.presentation.script_list.ui.time_picker.HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        value = state,
        onValueChange = {
            state = it
        },
        hoursDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "hours"
            )
        },
        minutesDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "minutes"
            )
        }
    )
}

@Composable
private fun DoublesPicker() {
    val possibleValues = generateSequence(0.5f, { it + 0.25f })
        .takeWhile { it <= 5f }
        .toList()
    var state by remember { mutableStateOf(possibleValues[0]) }
    ListItemPicker(
        label = { it.toString() },
        value = state,
        onValueChange = { state = it },
        list = possibleValues
    )
}

@Composable
private fun FruitPicker() {
    val possibleValues = listOf("🍎", "🍊", "🍉", "🥭", "🍈", "🍇", "🍍")
    var state by remember { mutableStateOf(possibleValues[0]) }
    ListItemPicker(
        label = { it },
        value = state,
        onValueChange = { state = it },
        list = possibleValues
    )
}

@Composable
private fun IntRangePicker() {
    val possibleValues = (-5..10).toList()
    var value by remember { mutableStateOf(possibleValues[0]) }
    ListItemPicker(
        label = { it.toString() },
        value = value,
        onValueChange = { value = it },
        list = possibleValues
    )
}
