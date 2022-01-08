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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.conditioning511.domain.script_list.models.DayGroup
import com.example.conditioning511.domain.script_list.models.RoomGroups
import com.example.conditioning511.domain.script_list.models.TestScript
import com.example.conditioning511.presentation.script_list.ui.add_script_ui.GiveNameDialog
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScriptList(navController: NavController, viewModel: ScriptListViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val scripts = viewModel.getListOfScripts()
    val filteredScripts: ArrayList<TestScript>
    val searchedText = textState.value.text
    val coroutineScope = rememberCoroutineScope()
    val textField = remember { mutableStateOf(TextFieldValue("")) }
    val roomIndex = remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    filteredScripts = if (searchedText.isEmpty()) {
        scripts
    } else {
        val resultList = ArrayList<TestScript>()
        for (script in scripts) {
            if (script.name.contains(searchedText.lowercase(Locale.getDefault()))
            ) {
                resultList.add(script)
            }
        }
        resultList
    }
    if (filteredScripts.isEmpty()) ScriptsNotFound()
    else if (searchedText.isNotEmpty()) ResultTextWidget()
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
                SheetContent(index = roomIndex.value, coroutineScope = coroutineScope, deleteState = deleteState, navController, viewModel)
            },
        ) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        modifier = Modifier.offset(y = (-60).dp, x = -(10).dp),
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
                        onClick = {
                            openDialog.value = true
                        },
                        contentColor = Color.White,
                        backgroundColor = Color.Blue,
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add new script")
                    }
                }
            ) {
                Column(
                ) {
                    SearchView(textState)
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(filteredScripts) { filteredScript ->
                            ScriptListItem(
                                script = filteredScript,
                                onItemClick = { selectedScript ->
                                    coroutineScope.launch {
                                        if (!sheetState.isVisible) {
                                            sheetState.show()
                                            roomIndex.value = selectedScript.sc_id
                                            textField.value = TextFieldValue(selectedScript.name)
                                        } else {
                                            sheetState.hide()
                                            textField.value = TextFieldValue("")
                                        }
                                    }
                                }
                            )
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
    index: String,
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
                    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                    val jsonAdapter = moshi.adapter(RoomGroups::class.java).lenient()
                    val userJson = jsonAdapter.toJson(viewModel.getScript(index))
                    navController.navigate(
                        "script/{details}".replace("{details}", userJson)
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
    ScriptList(navController = navController, ScriptListViewModel())
}
