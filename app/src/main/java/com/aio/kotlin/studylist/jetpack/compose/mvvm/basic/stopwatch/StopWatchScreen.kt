package com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.stopwatch

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopWatchApp(viewModel: StopWatchViewModel) {
    StopWatchScreen(
        elapsedTime = viewModel.timeInMillis.collectAsState().value,
        onStart = { viewModel.startStopWatch() },
        onPause = { viewModel.pauseStopWatch() },
        onReset = { viewModel.resetStopWatch() },
        isRunning = viewModel.isRunning.collectAsState().value
    )
}

@Composable
fun StopWatchScreen(
    elapsedTime: Long,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit,
    isRunning: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val stopWatchFunction = if (isRunning) onPause else onStart
        val stopWatchText = if (isRunning) "Pause" else "Start"

        Log.d("StopWatchScreen", "--- Start ---")

        Text(
            text = "Stopwatch",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = formatTime(elapsedTime),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = stopWatchFunction) {
                Text(text = stopWatchText)
            }
            Button(onClick = onReset) {
                Text(text = "Reset")
            }
        }
    }
}

fun formatTime(timeInMillis: Long): String {
    val milliseconds = timeInMillis % 1000 / 10
    val seconds = (timeInMillis / 1000) % 60
    val minutes = (timeInMillis / 1000) / 60
    return "%02d:%02d:%02d".format(minutes, seconds, milliseconds)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StopWatchScreen(
        elapsedTime = 0,
        onStart = { /*TODO*/ },
        onPause = { /*TODO*/ },
        onReset = { /*TODO*/ },
        isRunning = false
    )
}