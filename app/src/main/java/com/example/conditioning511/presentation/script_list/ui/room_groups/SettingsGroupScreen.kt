package com.example.conditioning511.presentation.script_list.ui.room_groups

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultStrokeLineWidth
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.presentation.script_list.ui.add_script_ui.ChooseDevicesDialog
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import kotlin.math.roundToInt

@Composable
fun SettingsGroupScreen(viewModel: ScriptListViewModel, navController: NavHostController) {
    val openChooseSettingsDialog = remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val index = viewModel.indexSettingGroupStateFlow.collectAsState().value
    val roomName = viewModel.dayGroupStateFlow.collectAsState().value?.get(index)?.days.toString()
    val settingsGroup = viewModel.settingGroupStateFlow.collectAsState().value

    ChooseDevicesDialog(openChooseSettingsDialog, screenWidth)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = roomName,
                color = Color.Black,
                fontSize = 26.sp,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                settingsGroup?.let { setsGroups ->
                    itemsIndexed(setsGroups) { index, it ->
                        SettingsGroupsItem(it)
                    }
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 90.dp)
                .height(50.dp),
            onClick = {
                openChooseSettingsDialog.value = true
            },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
        ) {
            Text("Новая группа", color = Color.White, fontSize = 18.sp)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsGroupsItem(
    it: ScriptDetailsModel.RoomGroup.DayGroup.Setting,
) {
    val squareSize = 90.dp
    val swipeableState = rememberSwipeableState(1)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val width = screenWidth * 1.2
    Box(
        modifier = Modifier
            .requiredWidth(width.toInt().dp)
            .height(110.dp)
            .padding(20.dp)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color(0x00E5E5E5))
            .offset(x = (-60).dp)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .fillMaxSize()
                .background(Color(0x00E5E5E5)),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .height(110.dp)
                        .width(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            it.time,
                            color = Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Icon(Icons.Default.Home, "домик", modifier = Modifier.size(23.dp))
                            Icon(
                                Icons.Default.ThumbUp,
                                "можно шум или нет",
                                modifier = Modifier.size(23.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Box(
                    modifier = Modifier
                        .background(Color.LightGray, RoundedCornerShape(10.dp))
                        .fillMaxHeight().width(squareSize*3)) {
                    Column(
                        Modifier.fillMaxSize()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.Top,
                        ) {
                            Spacer(modifier = Modifier.padding(5.dp))
                            Text(
                                it.temp.toString() + "C",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(
                                it.co2.toString() + "%",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(
                                it.hum.toString() + "ppm",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Row(
                            Modifier.wrapContentWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            Spacer(modifier = Modifier.padding(7.dp))
                            Icon(
                                Icons.Default.Home,
                                "домик",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .size(20.dp)
                            )
                            Icon(
                                Icons.Default.ThumbUp,
                                "можно шум или нет",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .size(20.dp)
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .width(110.dp)
                        .offset(x = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(Icons.Default.Edit, "исправить", modifier = Modifier
                        .size(25.dp)
                        .clickable {

                        })
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "удалить",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {

                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingPreview() {
    SettingsGroupsItem(
        ScriptDetailsModel.RoomGroup.DayGroup.Setting(
            atHome = 0,
            co2 = 100,
            dontUse = listOf(),
            hum = 400,
            mustUse = listOf(),
            mute = 1,
            temp = 23,
            time = "14:00"
        )
    )
}