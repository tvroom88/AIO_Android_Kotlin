package com.aio.kotlin.studylist.jetpack.compose.sideeffect

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * SideEffects
 * 1. LaunchedEffect
 * 2. SideEffect
 * 3. DisposableEffect
 */
@Composable
fun SideEffectScreen(context: Context) {
    Column {
        LaunchedEffectExample()
        Spacer(modifier = Modifier.height(10.dp))
        SideEffectExample()
        Spacer(modifier = Modifier.height(10.dp))
        DisposableEffectExample(context)
    }
}

// 1. LaunchedEffect
@Composable
fun LaunchedEffectExample() {
    var count by remember { mutableIntStateOf(0) }
    var msgChangeOnce by remember { mutableStateOf("한번만 변경되는 메시지 입니다.") }
    var msg by remember { mutableStateOf("버튼을 눌러보세요!") }

    // 1. 처음 한번만 실행되는 LaunchedEffect(Unit)
    LaunchedEffect(Unit) { // Composable이 최초 실행될 때만 실행됨
        delay(2000)
        msgChangeOnce = "2초가 지나 변경되었습니다. 버튼 클릭과는 무관합니다."
    }

    // 2. count가 변경될 때마다 실행됨
    LaunchedEffect(count) {
        msg = "Count: $count"
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = Color.Cyan)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = msgChangeOnce,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = msg, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("증가")
        }
    }
}

// 2. SideEffect
@Composable
fun SideEffectExample() {
    var count by remember { mutableIntStateOf(0) }

    SideEffect {
        Log.d("SideEffectExample", "Count 값이 변경됨: $count")
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: $count", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("증가")
        }
    }
}

// 3. DisposableEffect
@Composable
fun DisposableEffectExample(context: Context) {
    var isAirplaneModeOn by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isAirplaneModeOn) "비행기 모드: 켜짐" else "비행기 모드: 꺼짐",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        DisposableEffect(context) {
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                        val state = intent.getBooleanExtra("state", false)
                        isAirplaneModeOn = state
                        Log.d("DisposableEffect", "Airplane mode changed: $state")
                    }
                }
            }
            val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            context.registerReceiver(receiver, filter)
            Log.d("DisposableEffect", "Receiver registered")

            onDispose {
                context.unregisterReceiver(receiver)
                Log.d("DisposableEffect", "Receiver unregistered")
            }
        }
    }
}

@Preview
@Composable
fun ABC() {
    val context = LocalContext.current
    DisposableEffectExample(context)
}