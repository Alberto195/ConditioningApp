package com.example.conditioning511.presentation.rooms.ui

import android.widget.GridLayout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conditioning511.domain.rooms.models.Room
import com.example.conditioning511.presentation.rooms.viewmodels.RoomsScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoomsScreen(roomViewModel: RoomsScreenViewModel) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 100.dp
        ),
        content = {
            items(roomViewModel.getRoomsList().size) { index ->
                RoomItem(index, roomViewModel.getRoomsList())
            }
        }
    )
}

@Composable
fun RoomItem(index: Int, list: List<Room>) {
    Card(
        backgroundColor = White,
        modifier = Modifier
            .padding(16.dp)
            .wrapContentWidth(),
        elevation = 8.dp,
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
                for (i in 1..list[index].people!!) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Amount of People"
                    )
                }
            }
            Text(
                text = list[index].date!!,
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
                Column(
                    modifier = Modifier.requiredWidth(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Temperature Icon"
                    )
                    Text(
                        text = list[index].temp.toString(),
                        color = Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Column(
                    modifier = Modifier.requiredWidth(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Humidity Icon"
                    )
                    Text(
                        text = list[index].hum.toString(),
                        color = Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Column(
                    modifier = Modifier.requiredWidth(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "CO2 Icon"
                    )
                    Text(
                        text = list[index].co2.toString(),
                        color = Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MaterialTheme {
    }
}

