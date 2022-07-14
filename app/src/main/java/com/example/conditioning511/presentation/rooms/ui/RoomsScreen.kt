package com.example.conditioning511.presentation.rooms.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.conditioning511.R
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.presentation.rooms.viewmodels.RoomsScreenViewModel

@Composable
fun RoomsScreen(roomViewModel: RoomsScreenViewModel) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (header, roomItems) = createRefs()
        Image(
            painterResource(R.drawable.blue_header),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .size(274.dp)
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
        RowColumn(roomViewModel, modifier = Modifier.constrainAs(roomItems) {
            top.linkTo(parent.top, margin = 170.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
//    BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
//        Image(
//            painterResource(R.drawable.header_new),
//            contentDescription = "",
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.LightGray),
//        )
//        RowColumn(roomViewModel)
//    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoomItem(room: Room) {
    val openDialog = remember { mutableStateOf(false) }
    RoomDialog(openDialog, room)
    Card(
        backgroundColor = Color(0xFFF7FDFF),
        border = BorderStroke(2.dp, Color(0xFF0998FF).copy(alpha = 0.4f)),
        modifier = Modifier
            .padding(16.dp)
            .width(160.dp)
            .shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(8.dp),
            ),
        elevation = 0.dp,
        onClick = { openDialog.value = true },
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .wrapContentWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding()
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.union),
                    contentDescription = "Amount of People",
                    modifier = Modifier
                        .padding(6.dp)
                        .size(20.dp)
                )
                Text(
                    text = room.people.toString(),
                    color = Color(0xFFFFA65A),
                    fontSize = 20.sp,
                )
            }
            Text(
                text = room.name ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF2348A6),
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
                            painterResource(R.drawable.temp),
                            contentDescription = "temp icon",
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(vertical = 5.dp)
                        )
                        Text(
                            text = it.toString() + "C",
                            color = Black,
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
                            painterResource(R.drawable.new_co2),
                            contentDescription = "co2 icon",
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(vertical = 5.dp)
                        )
                        Text(
                            text = it.toString() + "ppm",
                            color = Black,
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
                            painterResource(R.drawable.drop),
                            contentDescription = "hum icon",
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(vertical = 5.dp)
                        )
                        Text(
                            text = "$it%",
                            color = Black,
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
    modifier: Modifier,
    content: @Composable () -> Unit = {
        roomViewModel.getRoomsList()
        val rooms = roomViewModel.roomsStateFlow.collectAsState().value
        if (rooms != null) {
            for (room in rooms) {
                RoomItem(room)
            }
        }
    }
) {
    Card(
        shape = RoundedCornerShape(32.dp),
        modifier = modifier,
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
                    xPos =
                        if (index % 2 == 1) (placeable.width * 1.03).toInt() else placeable.width / 9
                    yPosition = if (index % 2 == 1) yPosition1 else yPosition2
                    placeable.placeRelative(x = xPos, y = yPosition)
                    if (index % 2 == 0) yPosition2 += (placeable.height / 1.05).toInt() else yPosition1 += (placeable.height / 1.05).toInt()
                }
            }
        }
    }
}
