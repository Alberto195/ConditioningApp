package com.example.conditioning511.presentation.script_list.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.conditioning511.domain.script_list.models.RoomGroup
import com.example.conditioning511.domain.script_list.models.Script
import com.example.conditioning511.presentation.script_list.ui.add_script_ui.GiveNameDialog
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScriptList(
    navController: NavController,
    viewModel: ScriptListViewModel,
    bottomBarVisibility: MutableState<Boolean>
) {
    viewModel.getListOfScripts()
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val scripts = viewModel.scripts.collectAsState().value
    val filteredScripts: List<Script>
    val searchedText = textState.value.text
    val coroutineScope = rememberCoroutineScope()
    val textField = remember { mutableStateOf(TextFieldValue("")) }
    val roomIndex = remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }

    filteredScripts = if (searchedText.isEmpty()) {
        scripts ?: ArrayList<Script>()
    } else {
        val resultList = ArrayList<Script>()
        if (scripts != null) {
            for (script in scripts) {
                if (script.name?.contains(searchedText.lowercase(Locale.getDefault())) == true
                ) {
                    resultList.add(script)
                }
            }
        }
        resultList
    }
    val deleteState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    GiveNameDialog(openDialog, viewModel, navController)
    ModalBottomSheetLayout(
        sheetState = deleteState,
        sheetContent = {
            DeleteSheetConfirm()
        }
    ) {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                SheetContent(
                    name = textField.value.text,
                    coroutineScope = coroutineScope,
                    deleteState = deleteState,
                    navController,
                    viewModel,
                )
            },
        ) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        modifier = Modifier
                            .offset(y = (-60).dp, x = -(10).dp)
                            .size(60.dp),
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
                        onClick = {
                            openDialog.value = true
                        },
                        contentColor = Color.White,
                        backgroundColor = Color(0xFF6C95FF),
                        shape = RoundedCornerShape(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add new script",
                            modifier = Modifier.size(45.dp)
                        )
                    }
                }
            ) {
                Column(
                ) {
                    SearchView(textState)
                    if (filteredScripts.isEmpty()) ScriptsNotFound()
                    // else if (filteredScripts.isNotEmpty()) ResultTextWidget()
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(filteredScripts) { filteredScript ->
                            ScriptListItem(
                                script = filteredScript
                            ) { selectedScript ->
                                coroutineScope.launch {
                                    sheetState.show()
                                    roomIndex.value = selectedScript.scId ?: ""
                                    textField.value = TextFieldValue(selectedScript.name ?: "")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultTextWidget() {
    Text(
        text = "Результат",
        color = Color.Gray,
        modifier = Modifier.padding(16.dp),
        fontSize = 20.sp,
    )
}

@Composable
fun ScriptsNotFound() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Сценарии не найдены",
            color = Color.Gray,
            fontWeight = Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Попробуйте другой запрос",
            color = Color.Gray,
            fontSize = 20.sp,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetContent(
    name: String,
    coroutineScope: CoroutineScope,
    deleteState: ModalBottomSheetState,
    navController: NavController,
    viewModel: ScriptListViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
                .clickable {
                    viewModel.onNameChanged(name)
                    navController.navigate(
                        "script/room_groups"
                    )
                },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Просмотреть сценарий",
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f)
            )
            Text(
                text = "Просмотреть сценарий",
                color = Color.Gray,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(3f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
                .clickable {
                    coroutineScope.launch {
                        if (!deleteState.isVisible) {
                            deleteState.show()
                        } else {
                            deleteState.hide()
                        }
                    }
                },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Удалить",
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f)
            )
            Text(
                text = "Удалить",
                color = Color.Gray,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(15.dp)
                    .weight(3f)
            )
        }
    }
}

@Composable
fun DeleteSheetConfirm() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Вы уверенны, что хотите удалить этот сценарий?",
            color = Color.Gray,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(15.dp),
            fontWeight = Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Это действие невозможно отменить",
            color = Color.Gray,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(15.dp),
        )
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, Color.Black),
        ) {
            Text(
                text = "Удалить",
                color = Color.Gray,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScriptListPreview() {
    val navController = rememberNavController()
    // ScriptList(navController = navController, ScriptListViewModel())
}
