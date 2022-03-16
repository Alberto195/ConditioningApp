package com.example.conditioning511.presentation.script_list.ui.room_groups

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.conditioning511.data.core.models.ScriptDetailsModel
import com.example.conditioning511.presentation.script_list.ui.add_script_ui.ChooseRoomsDialog
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import kotlin.math.roundToInt

@Composable
fun RoomGroupsScreen(viewModel: ScriptListViewModel, navController: NavController) {
    val scriptName = viewModel.nameStateFlow.collectAsState().value
    val openChooseRoomsDialog = remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val roomGroupsList = viewModel.roomGroupListStateFlow.collectAsState().value
    ChooseRoomsDialog(openChooseRoomsDialog, screenWidth, viewModel, null)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = scriptName,
                color = Color.Black,
                fontSize = 26.sp,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                roomGroupsList?.forEachIndexed { i, script ->
                    if (script.name == scriptName) {
                        viewModel.indexRoomGroupStateFlow.value = i
                        itemsIndexed(script.roomGroups) { ind, it ->
                            RoomGroupItem(it, navController, viewModel, ind)
                        }
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
                openChooseRoomsDialog.value = true
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
fun RoomGroupItem(it: ScriptDetailsModel.RoomGroup, navController: NavController, viewModel: ScriptListViewModel, index: Int) {
    val squareSize = 90.dp
    val swipeableState = rememberSwipeableState(1)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color(0x00E5E5E5))
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .fillMaxWidth()
                .background(Color(0x00E5E5E5)),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                Text(
                    it.rIDs.toString(),
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.offset(x = -(20).dp)
                )
                Row(
                    modifier = Modifier
                        .width(150.dp)
                        .offset(x = 25.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(Icons.Default.Edit, "", modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            viewModel.changeRids(it.rIDs)
                            viewModel.indexDayGroupStateFlow.value = index
                            navController.navigate("script/day_groups")
                        })
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {

                            }
                    )
                }
            }
        }
    }
}