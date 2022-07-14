package com.example.conditioning511.presentation.script_list.ui.add_script_ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.conditioning511.presentation.script_list.ui.time_picker.AMPMHours
import com.example.conditioning511.presentation.script_list.ui.time_picker.Hours
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import com.example.conditioning511.presentation.theme.colorText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GiveNameDialog(
    openGiveNameDialog: MutableState<Boolean>,
    viewModel: ScriptListViewModel,
    navController: NavController
) {
    val scriptName = viewModel.nameStateFlow.collectAsState().value
    val openChooseRoomsDialog = remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    ChooseRoomsDialog(openChooseRoomsDialog, screenWidth, viewModel, navController)
    if (openGiveNameDialog.value) {
        Dialog(
            onDismissRequest = {
                openGiveNameDialog.value = false
            },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            DialogTemplate(openGiveNameDialog, screenWidth, {
                navController.navigate("script/room_groups")
                viewModel.onNewScriptDetail()
            }) {
                Text(
                    text = "Новый сценарий",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Введите придуманное название",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                TextField(
                    value = scriptName,
                    onValueChange = viewModel::onNameChanged,
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 20.dp)
                        .border(
                            BorderStroke(1.dp, color = Color(0xFF6C95FF)),
                            shape = CircleShape
                        ),
                    shape = RoundedCornerShape(50.dp),
                    singleLine = true,
                    colors = colorText(),
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChooseRoomsDialog(
    openChooseRoomsDialog: MutableState<Boolean>,
    screenWidth: Int,
    viewModel: ScriptListViewModel,
    navController: NavController?
) {
    viewModel.getRooms()
    val roomsCheckedArray = arrayListOf<Int>()
    val openChooseDatesDialog = remember { mutableStateOf(false) }
    val roomList = viewModel.roomsStateFlow.collectAsState().value

    if (navController != null) {
        ChooseDatesDialog(openChooseDatesDialog, screenWidth, viewModel, navController)
    }
    if (openChooseRoomsDialog.value) {
        Dialog(
            onDismissRequest = {
                openChooseRoomsDialog.value = false
            },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            DialogTemplate(openChooseRoomsDialog, screenWidth, {
                viewModel.indexDayGroupStateFlow.value =
                    viewModel.roomGroupStateFlow.value.list?.size ?: 0
                viewModel.changeRids(roomsCheckedArray.toList())
            }) {
                Text(
                    text = "Комнаты",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Выберите комнаты для группировки",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.width((screenWidth / 1.9).dp),
                    maxLines = 4,
                    textAlign = TextAlign.Center,
                )
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.heightIn(0.dp, (screenWidth / 2.4).dp),
                ) {
                    items(roomList ?: listOf()) { room ->
                        val checkBox = remember { mutableStateOf(false) }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Checkbox(
                                checked = checkBox.value,
                                onCheckedChange = { checked ->
                                    checkBox.value = checked
                                    if (checked) roomsCheckedArray.add(room.rId)
                                    else roomsCheckedArray.remove(room.rId)
                                },
                                modifier = Modifier.padding(3.dp),
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF6C95FF),
                                    disabledColor = Color(0xFFEEF6FF)
                                )
                            )
                            Text(
                                room.name ?: "",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .width(140.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChooseDatesDialog(
    openChooseDatesDialog: MutableState<Boolean>,
    screenWidth: Int,
    viewModel: ScriptListViewModel,
    navController: NavController?
) {
    val datesCheckedArray = arrayListOf<Int>()
    if (openChooseDatesDialog.value) {
        Dialog(
            onDismissRequest = {
                openChooseDatesDialog.value = false
            },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            DialogTemplate(openChooseDatesDialog, screenWidth, {
                viewModel.indexSettingGroupStateFlow.value =
                    viewModel.settingGroupStateFlow.value.list?.size ?: 0
                viewModel.daysChanged(datesCheckedArray)
            }) {
                Text(
                    text = "Дни недели",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(7.dp)
                )
                Text(
                    text = "Выберите дни, по которым будут применяться настройки",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width((screenWidth / 1.4).dp)
                        .padding(12.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .offset(y = 0.dp)
                ) {
                    for (i in 0 until 7) {
                        Check(i, getDayString(i), datesCheckedArray)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChooseTimeDialog(
    openChooseTimeDialog: MutableState<Boolean>,
    screenWidth: Int,
    viewModel: ScriptListViewModel,
    navController: NavController?
) {
    if (openChooseTimeDialog.value) {
        Dialog(
            onDismissRequest = {
                openChooseTimeDialog.value = false
            },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            DialogTemplate(openChooseTimeDialog, screenWidth, {
                openChooseTimeDialog.value = false
            }) {
                Text(
                    text = "Время",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(7.dp)
                )
                Text(
                    text = "Выберите время 1-ой настройки",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width((screenWidth / 2.4).dp)
                        .padding(12.dp)
                )
                HoursNumberPicker(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChooseDevicesDialog(openChooseDevicesDialog: MutableState<Boolean>, screenWidth: Int) {
    if (openChooseDevicesDialog.value) {
        Dialog(
            onDismissRequest = {
                openChooseDevicesDialog.value = false
            },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            DialogTemplate(openChooseDevicesDialog, screenWidth, {}) {
                Text(
                    text = "Время",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(7.dp)
                )
                Text(
                    text = "Выберите время 1-ой настройки",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width((screenWidth / 2.4).dp)
                        .padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun DialogTemplate(
    thisDialogState: MutableState<Boolean>,
    screenWidth: Int,
    action: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .wrapContentSize()
            .width((screenWidth / 1.3).dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close dialog",
                    modifier = Modifier
                        .clickable {
                            thisDialogState.value = false
                        }
                        .padding(10.dp)
                        .size(30.dp)
                )
            }
            content()
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                onClick = {
                    action()
                    thisDialogState.value = false
                },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6C95FF))
            ) {
                Text("Выбрать", color = Color.White)
            }
        }
    }
}

@Composable
private fun HoursNumberPicker(viewModel: ScriptListViewModel?) {
    var state by remember { mutableStateOf<Hours>(AMPMHours(12, 0, AMPMHours.DayTime.PM)) }
    com.example.conditioning511.presentation.script_list.ui.time_picker.HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 20.dp),
        dividersColor = Color.Gray,
        value = state,
        onValueChange = {
            state = it
            viewModel?.onTimeChanged(it.hours.toString() + ":" + it.minutes.toString())
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

@Preview
@Composable
fun TestHoursNumberPicker() {
    HoursNumberPicker(null)
}

@Composable
fun Check(index: Int, text: String, datesCheckedArray: ArrayList<Int>) {
    val textColor = remember { mutableStateOf(Color.Black) }
    val backgroundColor = remember { mutableStateOf(Color.LightGray) }
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(40.dp)
            .background(Color.Gray)
            .padding(3.dp)
            .clip(CircleShape)
            .background(backgroundColor.value)
            .clickable {
                if (textColor.value == Color.Black) {
                    textColor.value = Color.White
                    backgroundColor.value = Color.Black
                    datesCheckedArray.add(index)
                } else {
                    textColor.value = Color.Black
                    backgroundColor.value = Color.LightGray
                    datesCheckedArray.remove(index)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor.value)
    }
}

fun getDayString(index: Int): String {
    return arrayListOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")[index]
}
