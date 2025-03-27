package com.aio.kotlin.studylist.jetpack.compose.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun mBackgroundAndRounding() {
    Column {
        explain("3. Modifier.background로 배경색 설정하고 라운딩 처리하기")
        Row{
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .background(
                        color = Color.Blue,
                        shape = RectangleShape
                    )
            ) {}
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .background(
                        color = Color.Blue,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {}
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .background(
                        color = Color.Blue,
                        shape = CircleShape
                    )
            ) {}
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .background(
                        color = Color.Blue.copy(alpha = 0.5f),
                        shape = CutCornerShape(10.dp),
                    )

            ) {}
        }

    }
}

@Composable
fun mBackgroundBrush() {
    Box(
        modifier = Modifier
            .width(30.dp)
            .height(30.dp)
            .background(
                brush = Brush.verticalGradient(listOf(Color.Blue, Color.Green)),
                shape = RectangleShape
            )
    ) {}
}

@Composable
fun mBorder() {
    Box(
        modifier = Modifier
            .width(30.dp)
            .height(30.dp)
            .border(
                width = 3.dp,
                color = Color.Green,
                shape = RectangleShape
            )
    ) { }
}

@Composable
fun BackgroundScreen(){
    Column(){
        mBackgroundAndRounding()
        Spacer(modifier = Modifier.height(1.dp)) // 위쪽 마진 효과
        mBackgroundBrush()
        Spacer(modifier = Modifier.height(1.dp)) // 위쪽 마진 효과
        mBorder()
    }
}