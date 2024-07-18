package com.aio.kotlin.studylist.jetpack.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ComposeExample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewText()
        }

//        setContent {
//            MyApp()
//        }
    }
}

@Composable
fun NewText() {

    Surface(color = Color.Green) {
        Text(
            text = "hi"
        )
    }

}

@Preview(showBackground = true, name = "Text preview")
@Composable
fun GreetingPreview() {
    NewText()
}

@Composable
fun MyApp() {
    var firstText by remember { mutableStateOf("Hello, first Text!") }
    var secondText by remember { mutableStateOf("Hello, second Text!") }

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                // Text to display
                Text(
                    text = firstText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green
                )

                Text(
                    text = secondText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Button to change the text
                Button(
                    onClick = {
                        secondText = "You clicked the button!"
                    }
                ) {
                    Text("Click me!")
                }
            }
        }
    }
}