package com.aio.kotlin.studylist.jetpack.compose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

//

/**
 * @Composable : 주석으로 이 함수가 UI를 그리는 함수라고 알려준다.
 * @Preview : 미리보기 함수
 *
 */
class ComposeExample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstComposeApp()
        }
    }
}

@Composable
private fun MyFirstComposeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NewText("text")
    }
}


@Composable
fun NewText(name: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Text(
            text = "1 $name",
            color = Color.Green,
        )

        Text(
            text = "2",
            color = Color.Yellow,
        )

        Text(
            text = "3",
            color = Color.Black,
        )
    }

}



//@Composable
//fun MyApp() {
//    var firstText by remember { mutableStateOf("Hello, first Text!") }
//    var secondText by remember { mutableStateOf("Hello, second Text!") }
//
//    MaterialTheme {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.TopCenter
//        ) {
//
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//            ) {
//                // Text to display
//                Text(
//                    text = firstText,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Green
//                )
//
//                Text(
//                    text = secondText,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Blue
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Button to change the text
//                Button(
//                    onClick = {
//                        secondText = "You clicked the button!"
//                    }
//                ) {
//                    Text("Click me!")
//                }
//            }
//        }
//    }
//}