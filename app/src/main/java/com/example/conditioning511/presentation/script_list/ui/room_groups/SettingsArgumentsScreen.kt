package com.example.conditioning511.presentation.script_list.ui.room_groups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.conditioning511.R
import com.example.conditioning511.presentation.script_list.ui.add_script_ui.ChooseTimeDialog
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsArgumentsScreen(viewModel: ScriptListViewModel, navController: NavController) {
    val index = viewModel.indexArgumentsStateFlow.collectAsState().value
    val settings = viewModel.settingGroupStateFlow.collectAsState().value
    val openTimeDialog = remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val flag = settings.list?.isNotEmpty()

    val time = viewModel.timeStateFlow.collectAsState().value
    val co2 = remember { mutableStateOf("") }
    val hum = remember { mutableStateOf("") }
    val temp = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    if (flag == true) {
        co2.value = settings.list[index].co2.toString()
        hum.value = settings.list[index].hum.toString()
        temp.value = settings.list[index].temp.toString()
    }

    ChooseTimeDialog(openTimeDialog, screenWidth, viewModel, navController)
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 40.dp)
            ) {
                Text(
                    text = "Параметры",
                    color = Color.Black,
                    fontSize = 26.sp,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Text(text = "Время", color = Color.Black, fontSize = 20.sp)
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .size(width = 150.dp, height = 50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Transparent)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(10.dp),
                                color = Color.Gray
                            )
                            .clickable {
                                openTimeDialog.value = true
                            }
                    ) {
                        Text(
                            text = time,
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "CO2", color = Color.Black, fontSize = 20.sp)
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .size(width = 150.dp, height = 50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Transparent)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(10.dp),
                                color = Color.Gray
                            )
                    ) {
                        TextField(value = co2.value,
                            onValueChange = {
                                co2.value = it
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color(0xFFE5E5E5),
                                focusedIndicatorColor =  Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Text(text = "Влажность", color = Color.Black, fontSize = 20.sp)
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .size(width = 150.dp, height = 50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Transparent)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(10.dp),
                                color = Color.Gray
                            )
                    ) {
                        TextField(
                            value = hum.value,
                            onValueChange = {
                                hum.value = it
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color(0xFFE5E5E5),
                                focusedIndicatorColor =  Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Text(text = "Температура", color = Color.Black, fontSize = 20.sp)
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .size(width = 150.dp, height = 50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Transparent)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(10.dp),
                                color = Color.Gray
                            )
                    ) {
                        TextField(value = temp.value,
                            onValueChange = {
                                temp.value = it
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color(0xFFE5E5E5),
                                focusedIndicatorColor =  Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 20.dp)
                ) {
                    Text(text = "Не дома", color = Color.Black, fontSize = 20.sp)
                    Text(text = "Без шума", color = Color.Black, fontSize = 20.sp)
                }
            }
        }
        Text(
            text = "Обязательные уст-ва",
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 40.dp)
        )
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            if (flag == true) {
                itemsIndexed(settings.list[index].mustUse) { i, mustUse ->
                    ArgumentsItem(i, viewModel)
                }
            }
        }
        Text(
            text = "Запрещенные уст-ва",
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
        )
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            if (flag == true) {
                itemsIndexed(settings.list[index].dontUse) { i, dontUse ->
                    ArgumentsItem(i, viewModel)
                }
            }
        }
        Button(onClick = {
            viewModel.settingsChanged(
                co2 = co2.value.toInt(),
                hum = hum.value.toInt(),
                temp = temp.value.toInt(),
                dontUse = viewModel.dontUseStateFlow.value,
                mustUse = viewModel.mustUseStateFlow.value,
                atHome = 1,
                mute = 1,
            )
        }) {
            Text(text = "save")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArgumentsItem(
    mustUseIndex: Int,
    viewModel: ScriptListViewModel?,
) {
    val backgroundColor = remember { mutableStateOf(Color(0xFF6C95FF)) }
    val btn = remember { mutableStateOf(R.drawable.add_new_one) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.padding(15.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(backgroundColor.value)
                    .padding(10.dp)
            ) {
                Image(
                    painterResource(R.drawable.outer_fan),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                )
                Image(
                    painterResource(R.drawable.group),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier.padding(10.dp)
                )

            }
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(
                        backgroundColor.value
                    )
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = Color.White
                    )
            ) {
                Image(
                    painterResource(btn.value),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .padding(7.dp)
                        .clickable {
                            if (backgroundColor.value == Color(0xFF6C95FF)) {
                                backgroundColor.value = Color(0xFF2348A6)
                                btn.value = R.drawable.delete_new_one
                                // datesCheckedArray.add(index)
                            } else {
                                backgroundColor.value = Color(0xFF6C95FF)
                                btn.value = R.drawable.add_new_one
                                // datesCheckedArray.remove(index)
                            }
                        }
                )
            }
        }
        Text(
            text = "Обогреватель",
            modifier = Modifier.width(65.dp),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}
