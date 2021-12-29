package com.example.conditioning511.presentation.rooms.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        backgroundColor = White,
        modifier = Modifier
            .padding(16.dp)
            .wrapContentWidth(),
        elevation = 8.dp,
        onClick = { openDialog.value = true }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .wrapContentWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .wrapContentWidth()
            ) {
                room.people?.let {
                    for (i in 1..it) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Amount of People"
                        )
                    }
                }
            }
            Text(
                text = room.date!!,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Gray,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(8.dp)
                    .width(150.dp)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .wrapContentWidth()
            ) {
                room.temp?.let {
                    Column(
                        modifier = Modifier.requiredWidth(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Temperature Icon"
                        )
                        Text(
                            text = it.toString(),
                            color = Gray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
                room.hum?.let {
                    Column(
                        modifier = Modifier.requiredWidth(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = "Humidity Icon"
                        )
                        Text(
                            text = it.toString(),
                            color = Gray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
                room.co2?.let {
                    Column(
                        modifier = Modifier.requiredWidth(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "CO2 Icon"
                        )
                        Text(
                            text = it.toString(),
                            color = Gray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    RoomItem(
        Room(
            rId = 2,
            co2 = 300,
            date = "3030",
            temp = 23,
            temp_value = null,
            hum = 58,
            people = 3
        )
    )
}

@Composable
fun RowColumn(
    roomViewModel: RoomsScreenViewModel,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {
        for (index in 0 until roomViewModel.getRoomsList().size) {
            RoomItem(roomViewModel.getRoomsList()[index])
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
                xPos = if (index % 2 == 1) (placeable.width * 0.93).toInt() else 0
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