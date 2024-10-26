package com.aio.kotlin.studylist.jetpack.compose.layouts

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.aio.kotlin.studylist.jetpack.compose.theme.DiverseComposeLayoutsTheme

class ComposeBList : ComponentActivity() {
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