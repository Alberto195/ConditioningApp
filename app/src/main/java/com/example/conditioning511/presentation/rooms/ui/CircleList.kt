package com.example.conditioning511.presentation.rooms.ui

import android.graphics.Typeface
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontListFontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.conditioning511.R
import java.lang.Math.sin
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun SmartCirclesList(roomName: String?, map: Map<String, Int>, showDialog: MutableState<Boolean>) {
    val id = map["id"]
    val amountOfPoints = map.size - 1
    val eachAngle = (amountOfPoints - 2) * 180 / amountOfPoints
    val centerAngle = 180 - eachAngle
    val tangentAngle = centerAngle / 2f
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val configuration = LocalConfiguration.current
        val Rad = configuration.screenWidthDp / 1.5f
        val r = abs(Rad * cos(Math.toRadians(centerAngle / 2.0)))
        val AB = abs(2 * sqrt(Rad.pow(2) - r.pow(2)))
        val y = abs(AB * sin(Math.toRadians(tangentAngle.toDouble())))
        val x = abs(AB * cos(Math.toRadians(tangentAngle.toDouble())))
        val (changeTempText, tempButton, midButton, exitIcon, addIcon, substractIcon, roomText) = createRefs()
        val temp = remember { mutableStateOf(map["temp"]) }
        Icon(
            Icons.Default.Clear,
            contentDescription = "exit",
            modifier = Modifier
                .size(40.dp)
                .clickable { showDialog.value = false }
                .constrainAs(exitIcon) {
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            tint = Color(0xFF000000)
        )
        Text(
            text = roomName ?: id.toString(), color = Color.Black, fontSize = 24.sp,
            fontFamily = FontFamily(
                ResourcesCompat.getFont(LocalContext.current, R.font.inder) ?: Typeface.DEFAULT
            ),
            modifier = Modifier
                .constrainAs(roomText) {
                    top.linkTo(parent.top, margin = 30.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
        )
        Text(
            text = "Изменить температуру", color = Color(0xFF2348A6), fontSize = 22.sp,
            fontWeight = Bold,
            fontFamily = FontFamily(
                ResourcesCompat.getFont(LocalContext.current, R.font.inder) ?: Typeface.DEFAULT
            ),
            modifier = Modifier
                .constrainAs(changeTempText) {
                    top.linkTo(parent.top, margin = 100.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
        )
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                Color(0xFF6C95FF), Rad * 1.3f,
                Offset(size.width / 2, size.height / 2),
                style = Stroke(width = 8f),
            )
        }
        Button(
            shape = CircleShape,
            border = BorderStroke(
                3.dp, Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF0998FF).copy(alpha = 0.9f),
                        Color(0xFF0998FF).copy(alpha = 0.8f),
                    )
                )
            ),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF6C95FF)
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
            border = BorderStroke(2.dp, Color(0xFF6C95FF)),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black,
                backgroundColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .size(100.dp)
                .constrainAs(tempButton) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom, margin = Rad.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onClick = { },
        ) {
            Text(
                temp.value.toString(),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                color = Color(0xFFFFA65A),
                fontWeight = Bold
            )
        }
        Icon(
            Icons.Default.Add,
            contentDescription = "plus",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    temp.value = (temp.value ?: 0) + 1
                }
                .constrainAs(addIcon) {
                    top.linkTo(tempButton.top)
                    start.linkTo(tempButton.end, margin = 10.dp)
                    bottom.linkTo(tempButton.bottom)
                },
            tint = Color(0xFF000000)
        )
        Image(
            painterResource(id = R.drawable.minus),
            contentDescription = "minus",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    temp.value = (temp.value ?: 0) - 1
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
            var (marginStart, marginTop) = countMargins(i, amountOfPoints, Rad, x, y)
            var marginEnd = 0.0
            if (marginStart < 0) {
                marginEnd = -marginStart
                marginStart = 0.0
            }
            Button(
                enabled = false,
                shape = CircleShape,
                border = BorderStroke(1.dp, Color(0xFF6C95FF)),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color(0xFFF7FDFF)
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
                    IconFromName(
                        mMap.keys.elementAt(i - 1),
                        mMap[mMap.keys.elementAt(i - 1)].toString()
                    )
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
                painterResource(id = R.drawable.blue_people),
                contentDescription = "Amount of People",
                modifier = Modifier.size(30.dp)
            )
            Text(
                "$text чел",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        "co2" -> {
            Image(
                painterResource(id = R.drawable.blue_co2),
                contentDescription = "co2 icon",
                alignment = Alignment.Center,
                modifier = Modifier.size(30.dp)
            )
            Text(
                "$text ppm",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        "temp_value" -> {
            Image(
                painterResource(id = R.drawable.blue_temp),
                contentDescription = "temp icon",
                alignment = Alignment.Center,
                modifier = Modifier.size(25.dp)
            )
            Text(
                "$text C",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        "hum" -> {
            Image(
                painterResource(id = R.drawable.blue_drop),
                contentDescription = "hum icon",
                alignment = Alignment.Center,
                modifier = Modifier.size(25.dp)
            )
            Text(
                "$text%",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        else -> {
            Text(
                text,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Black
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
