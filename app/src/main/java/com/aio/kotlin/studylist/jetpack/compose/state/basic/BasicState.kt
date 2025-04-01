package com.aio.kotlin.studylist.jetpack.compose.state.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun BasicStateScreen(){
    BasicState()
    CounterScreen()
}

@Composable
fun BasicState() {
    var count by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: $count", fontSize = 24.sp)
        Button(onClick = { count++ }) {
            Text("Increase")
        }
    }
}

/**
 * Compose 함수에서 State를 갖게 사용하는 방법
 */
@Composable
fun StatefulCounter() {
    var count by remember { mutableIntStateOf(0) } // 내부에서 상태를 관리
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: $count")
        Button(onClick = { count++ }) {
            Text("Increase")
        }
    }
}

/**
 * Compose 함수에서 State를 외부에서 주입받게 하는 방법
 */
@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: $count")
        Button(onClick = onIncrement) {
            Text("Increase")
        }
    }
}

@Composable
fun CounterScreen() {
    var count by remember { mutableIntStateOf(0) }
    StatelessCounter(count = count, onIncrement = { count++ }) // 상태를 외부에서 관리
}

@Preview
@Composable
fun mBasicStatePreview(){
    BasicState()
}