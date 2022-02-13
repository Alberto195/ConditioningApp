package com.example.conditioning511.presentation.rooms.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.conditioning511.R
import java.lang.Math.sin
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun SmartCirclesList(map: Map<String, Int>, showDialog: MutableState<Boolean>) {
    val id = map["id"]
    val amountOfPoints = map.size - 1
    val eachAngle = (amountOfPoints - 2) * 180 / amountOfPoints
    val centerAngle = 180 - eachAngle
    val tangentAngle = centerAngle / 2f
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val configuration = LocalConfiguration.current
        val R = configuration.screenWidthDp / 1.5f
        val r = abs(R * cos(Math.toRadians(centerAngle / 2.0)))
        val AB = abs(2 * sqrt(R.pow(2) - r.pow(2)))
        val y = abs(AB * sin(Math.toRadians(tangentAngle.toDouble())))
        val x = abs(AB * cos(Math.toRadians(tangentAngle.toDouble())))
        val (tempButton, midButton, exitIcon, addIcon, substractIcon) = createRefs()
        Icon(
            Icons.Default.Clear,
            contentDescription = "exit",
            modifier = Modifier
                .size(40.dp)
                .clickable { showDialog.value = false }
                .constrainAs(exitIcon) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                },
            tint = Color(0xFFDAE4ED)
        )
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                Color.White, R,
                Offset(size.width / 2, size.height / 2),
                style = Stroke(width = 8f),
            )
        }
        Button(
            shape = CircleShape,
            border = BorderStroke(3.dp, Color(0xFF0998FF)),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF32C5FF)
            ),
            modifier = Modifier
                .size(150.dp)
                .constrainAs(midButton) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onClick = { },
        ) {
            Text("Проветрить комнату", textAlign = TextAlign.Center, fontSize = 20.sp)
        }
        Button(
            enabled = false,
            shape = CircleShape,
            border = BorderStroke(2.dp, Color(0xFFEDF1F3)),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Gray,
                backgroundColor = Color.White
            ),
            modifier = Modifier
                .size(100.dp)
                .constrainAs(tempButton) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom, margin = R.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onClick = { },
        ) {
            Text(map["temp"].toString(), textAlign = TextAlign.Center, fontSize = 24.sp)
        }
        Icon(
            Icons.Default.Add,
            contentDescription = "plus",
            modifier = Modifier
                .size(40.dp)
                .clickable { // TODO //
                }
                .constrainAs(addIcon) {
                    top.linkTo(tempButton.top)
                    start.linkTo(tempButton.end, margin = 10.dp)
                    bottom.linkTo(tempButton.bottom)
                },
            tint = Color(0xFFDAE4ED)
        )
        Icon(
            Icons.Default.Clear,
            contentDescription = "minus",
            modifier = Modifier
                .size(40.dp)
                .clickable { // TODO
                }
                .constrainAs(substractIcon) {
                    top.linkTo(tempButton.top)
                    end.linkTo(tempButton.start, margin = 10.dp)
                    bottom.linkTo(tempButton.bottom)
                },
        )
        val mMap = map.toMutableMap()
        mMap.let {
            it.remove("id")
            it.remove("temp")
        }
        for (i in 1 until amountOfPoints) {
            val ref = createRef()
            var (marginStart, marginTop) = countMargins(i, amountOfPoints, R, x, y)
            var marginEnd = 0.0
            if (marginStart < 0) {
                marginEnd = -marginStart
                marginStart = 0.0
            }
            Button(
                enabled = false,
                shape = CircleShape,
                border = BorderStroke(2.dp, Color(0xFFEDF1F3)),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0x00597393),
                    backgroundColor = Color.White
                ),
                modifier = Modifier
                    .size(100.dp)
                    .constrainAs(ref) {
                        top.linkTo(parent.top, margin = marginTop.dp)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = marginStart.dp)
                        end.linkTo(parent.end, margin = marginEnd.dp)
                    },
                onClick = { },
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconFromName(mMap.keys.elementAt(i-1), mMap[mMap.keys.elementAt(i-1)].toString())
                }
            }
        }
    }
}


@Composable
private fun IconFromName(name: String, text: String) {
    return when (name) {
        "temp" -> {
        }
        "people" -> {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.people_grey),
                contentDescription = "Amount of People",
                modifier = Modifier.padding(horizontal = 1.dp)
            )
            Text(
                "$text чел",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
        "co2" -> {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.co2_grey),
                contentDescription = "co2 icon",
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "$text ppm",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
        "temp_value" -> {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.temperature_grey),
                contentDescription = "temp icon",
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "$text C",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
        "hum" -> {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.humidity_grey),
                contentDescription = "hum icon",
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "$text%",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
        else -> {
            Text(
                text,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
    }
}

private fun countMargins(
    i: Int,
    amountOfPoints: Int,
    R: Float,
    x: Double,
    y: Double
): Pair<Double, Double> {
    if (amountOfPoints < 5) {
        return when (i) {
            amountOfPoints - 1 -> Pair(-x, y - R)
            1 -> Pair(x, y - R)
            2 -> Pair(x - y, x + y - R)
            3 -> Pair(y - x, x + y - R)
            else -> Pair(0.0, 0.0)
        }
    } else if (amountOfPoints == 5) {
        return when (i) {
            amountOfPoints - 1 -> Pair(-x, y - R)
            1 -> Pair(x, y - R)
            2 -> Pair(x - 0.5 * y, x + y - 0.85 * R)
            3 -> Pair(0.5 * y - x, x + y - 0.85 * R)
            else -> Pair(0.0, 0.0)
        }
    } else if (amountOfPoints == 6) {
        return when (i) {
            amountOfPoints - 1 -> Pair(-x, y - R)
            1 -> Pair(x, y - R)
            2 -> Pair(x, 3 * y - R)
            3 -> Pair(0.0, R * 1.0)
            4 -> Pair(-x, 3 * y - R)
            else -> Pair(0.0, 0.0)
        }
    } else return Pair(0.0, 0.0)
}

@Preview
@Composable
fun SmartCirclesPreview() {

}

@Composable
fun MyBasicColumn(
    amountOfPoints: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {
        for (i in 0 until amountOfPoints) {
            Button(
                enabled = false,
                shape = CircleShape,
                border = BorderStroke(2.dp, Color.Gray),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Gray,
                    backgroundColor = Color.White
                ),
                modifier = Modifier.size(100.dp),
                onClick = { },
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Star, contentDescription = "icon")
                    Text("23.69", textAlign = TextAlign.Center, fontSize = 14.sp)
                }
            }
        }
    }
) {
    val eachAngle = (amountOfPoints - 2) * 180 / amountOfPoints
    val centerAngle = 180 - eachAngle
    val tangentAngle = centerAngle / 2f
    val configuration = LocalConfiguration.current
    val R = configuration.screenWidthDp / 1.5f
    val r = abs(R * cos(Math.toRadians(centerAngle / 2.0)))
    val AB = abs(2 * sqrt(R.pow(2) - r.pow(2)))
    val k = (amountOfPoints - 2) * 180 / amountOfPoints
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        layout(
            constraints.maxWidth,
            constraints.maxHeight
        ) {
            placeables.forEachIndexed { i, placeable ->
                val kk = if (i == amountOfPoints - 1) -60 else k * (i + 1)
                val y =
                    AB * sin(Math.toRadians(tangentAngle.toDouble() + kk)) + (constraints.maxHeight - constraints.maxHeight * 0.1) / 2
                val x =
                    AB * cos(Math.toRadians(tangentAngle.toDouble() + kk)) + (constraints.maxWidth - constraints.maxWidth * 0.2) / 2
                placeable.placeRelative(x = x.toInt(), y = y.toInt())
            }
        }
    }
}

@Preview
@Composable
fun PreviewCircles() {
    MyBasicColumn(3)
}