package com.example.conditioning511.presentation.script_list.ui

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomDialog(bottomSheetScaffoldState: BottomSheetScaffoldState, textState: MutableState<TextFieldValue>) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = textState.value.text,
                    fontSize = 28.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }, sheetPeekHeight = 0.dp
    ) {

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun BottomDialogPreview() {
    BottomSheetSample()
}


@ExperimentalMaterialApi
@Composable
fun BottomSheetSample(){

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState= bottomSheetScaffoldState,

        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color(0xAA3fa7cc))
            ) {
                Text(
                    text = "Hello from bottom sheet",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        },
        sheetPeekHeight= 40.dp
    ) {
        MainscreenView( scope, bottomSheetScaffoldState )
    }

}

@Composable
@ExperimentalMaterialApi
fun MainscreenView(scope: CoroutineScope, bottomSheetScaffoldState : BottomSheetScaffoldState) {
    Box(
        Modifier
            .fillMaxWidth()
    ){
        Button(
            modifier = Modifier
                .padding(20.dp)
                .align(alignment = Alignment.TopCenter),
            onClick = {
                scope.launch{
                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            }
        ) {
            Text(
                text = "Click to show Bottom Sheet"
            )
        }
    }

}