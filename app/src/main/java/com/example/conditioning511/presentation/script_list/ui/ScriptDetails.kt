package com.example.conditioning511.presentation.script_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.domain.script_list.models.RoomGroup
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel

@Composable
fun ScriptDetails(script: RoomGroup?, viewModel: ScriptListViewModel) {
    val roomList = viewModel.roomsStateFlow.collectAsState().value
    Column {
        DaysBox(
            Icons.Default.Settings,
            "Дни недели",
            script?.dayGroups?.get(0)?.days.scriptDaysToString(),
        )
        DaysBox(Icons.Default.Home, "Комнаты", script?.rIDs.scriptRoomsToString(roomList))
    }
}

@Composable
fun DaysBox(
    icon: ImageVector,
    upperText: String,
    bottomText: String?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(icon, "", modifier = Modifier.size(20.dp))
            Column {
                Text(text = upperText)
                Text(text = bottomText ?: "")
            }
            Icon(Icons.Default.Edit, "", modifier = Modifier.size(20.dp))
        }
    }
}

private fun List<Int>?.scriptDaysToString(): String {
    val stringList = arrayListOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")
    val sb = StringBuilder()
    if (this != null) {
        for (index in this) {
            sb.append(stringList[index])
            sb.append(" ")
        }
    }
    return sb.toString()
}

private fun List<Int>?.scriptRoomsToString(roomList: List<Room>?): String {
    val sb = StringBuilder()
    if (this != null) {
        for (index in this) {
            sb.append(roomList?.get(index)?.name ?: "")
            sb.append(" ")
        }
    }
    return sb.toString()
}
