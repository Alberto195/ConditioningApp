package com.example.conditioning511.presentation.rooms.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.conditioning511.domain.rooms.models.Room

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoomDialog(showDialog: MutableState<Boolean>, roomInfo: Room) {
    val map = mutableMapOf<String, Int>()
    roomInfo.apply {
        temp?.let { map.put("temp", it) }
        people?.let { map.put("people", it) }
        co2?.let { map.put("co2", it) }
        temp_value?.let { map.put("temp_value", it) }
        hum?.let { map["hum"] = it }
        map["id"] = this.rId
    }
    if (showDialog.value) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = { showDialog.value = false }
        ) {
            ConstraintLayout {
                val circularList = createRef()
                Surface(modifier = Modifier
                    .constrainAs(circularList) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight()) {
                    SmartCirclesList(map = map.toMap(), showDialog)
                }
            }
        }
    }
}