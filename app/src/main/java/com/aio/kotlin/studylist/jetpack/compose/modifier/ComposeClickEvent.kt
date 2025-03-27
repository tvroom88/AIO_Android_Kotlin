package com.aio.kotlin.studylist.jetpack.compose.modifier

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun clickEvent() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .width(400.dp)
            .height(100.dp)
            .background(Color.Green)
            .clickable { showMessage(context, "클릭") }
    ) {
        Text(text = "클")
    }

}

@Composable
fun clickGesture() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .width(400.dp)
            .height(100.dp)
            .background(Color.Green)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { showMessage(context, "onTap") },
                    onPress = {
                        showMessage(context, "onPress")
                        Log.d("clickGesture", "onPress")
                    },
                    onDoubleTap = { showMessage(context, "onDoubleTap") },
                    onLongPress = { showMessage(context, "onLongPress") }
                )
            }

    ) {
        Text(text = "클")
    }
}

fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun ClickEventScreen() {
    Column {
        explain("4. 클릭 이벤트")
        clickEvent()
        Spacer(modifier = Modifier.height(1.dp)) // 위쪽 마진 효과
        clickGesture()
    }
}

