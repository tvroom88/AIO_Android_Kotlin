package com.aio.kotlin.studylist.jetpack.compose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.aio.kotlin.studylist.jetpack.compose.theme.DiverseComposeLayoutsTheme

class ComposeWebView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiverseComposeLayoutsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                }
            }
        }
    }
}

@Composable
fun CustomWebView() {

}