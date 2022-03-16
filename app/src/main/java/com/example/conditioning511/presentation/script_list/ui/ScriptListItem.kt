package com.example.conditioning511.presentation.script_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conditioning511.domain.script_list.models.Script
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScriptListItem(
    script: Script,
    onItemClick: (Script) -> Unit,
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clickable { onItemClick(script) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 28.dp)
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF2F7F9))
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .wrapContentWidth()
                ) {
                    Text(
                        text = script.name ?: "",
                        fontSize = 18.sp,
                        fontWeight = Bold,
                        color = Color(0xFF7F8B90),
                        modifier = Modifier
                            .padding(horizontal = 1.dp, vertical = 1.dp)
                    )
                    Text(
                        text = script.isCurrent.toString(),
                        fontSize = 18.sp,
                        color = Color(0xFF7F8B90),
                        modifier = Modifier
                            .padding(horizontal = 1.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = false)
@Composable
fun ScriptListItemPreview() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    ScriptListItem(script = Script(
        scId = "2020", name = "тестовый",
        isCurrent = false
    ), onItemClick = {
        coroutineScope.launch {
            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                bottomSheetScaffoldState.bottomSheetState.expand()
                textState.value = TextFieldValue(it.scId ?: "")
            } else {
                bottomSheetScaffoldState.bottomSheetState.collapse()
                textState.value = TextFieldValue("")
            }
        }
    })
}
