package com.example.conditioning511.presentation.rooms.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conditioning511.R
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.presentation.rooms.viewmodels.RoomsScreenViewModel

@Composable
fun RoomsScreen(roomViewModel: RoomsScreenViewModel) {
    RowColumn(roomViewModel)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoomItem(room: Room) {
    val openDialog = remember { mutableStateOf(false) }
    RoomDialog(openDialog, room)
    Card(
        backgroundColor = Color(0xFFBFD4E4),
        modifier = Modifier
            .padding(16.dp)
            .width(160.dp),
        elevation = 8.dp,
        onClick = { openDialog.value = true },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .wrapContentWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .wrapContentWidth()
            ) {
                room.people?.let {
                    for (i in 1..it) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.people),
                            contentDescription = "Amount of People",
                            modifier = Modifier.padding(horizontal = 1.dp)
                        )
                    }
                }
            }
            Text(
                text = room.name ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color(0xFF597393),
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(8.dp)
                    .width(150.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                room.temp?.let {
                    Column(
                        modifier = Modifier
                            .requiredWidth(32.dp)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.temperature),
                            contentDescription = "temp icon",
                            alignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = it.toString() + "C",
                            color = White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier,
                            maxLines = 1,
                        )
                    }
                }
                room.co2?.let {
                    Column(
                        modifier = Modifier
                            .requiredWidth(32.dp)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.co2),
                            contentDescription = "co2 icon",
                            alignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = it.toString() + "ppm",
                            color = White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier,
                            maxLines = 1,
                        )
                    }
                }
                room.hum?.let {
                    Column(
                        modifier = Modifier
                            .requiredWidth(32.dp)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.humidity),
                            contentDescription = "hum icon",
                            alignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "$it%",
                            color = White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomItemPreview() {
    RoomItem(
        Room(
            rId = 2,
            co2 = 300,
            date = "3030",
            temp = 23,
            temp_value = null,
            hum = 58,
            people = 3,
            name = "room"
        )
    )
}

@Composable
fun RowColumn(
    roomViewModel: RoomsScreenViewModel,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {
        for (room in roomViewModel.getRoomsList()) {
            RoomItem(room)
        }
    }
) {
    Layout(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        layout(constraints.maxWidth, constraints.maxHeight) {
            var yPosition = 0
            var yPosition1 = 0
            var yPosition2 = 0
            var xPos = 0
            placeables.forEachIndexed { index, placeable ->
                xPos = if (index % 2 == 1) (placeable.width * 1.076).toInt() else placeable.width / 15
                yPosition = if (index % 2 == 1) yPosition1 else yPosition2
                placeable.placeRelative(x = xPos, y = yPosition)
                if (index % 2 == 0) yPosition2 += placeable.height else yPosition1 += placeable.height
            }
        }
    }
}

@Composable
@Preview
fun PreviewMyBasicColumn() {
    RowColumn(RoomsScreenViewModel())
}