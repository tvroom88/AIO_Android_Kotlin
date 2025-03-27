package com.aio.kotlin.studylist.jetpack.compose.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 1. Modifier의 크기(너비, 높이) 조절하기
 * - Modifier.width(width: Dp) : 너비를 width.dp로 설정
 * - Modifier.height(height: Dp) : 높이를 height.dp로 설정
 * - Modifier.fillMaxWidth(fraction: Float = 1f) : 너비(가로) 전체 채우기, 기본은 모두 채우는 것(fraction = 1f)이며 전체 대비 채우는 비율을 설정하는 것도 가능
 * - Modifier.fillMaxHeight(fraction: Float = 1f) : 높이(세로) 전체 채우기, 기본은 모두 채우는 것(fraction = 1f)이며 전체 대비 채우는 비율을 설정하는 것도 가능
 * - Modifier.fillMaxSize(fraction: Float = 1f) : 너비, 높이 모두 전체 채우기, 기본은 모두 채우는 것(fraction = 1f)이며 전체 대비 채우는 비율을 설정하는 것도 가능
 */
@Composable
fun mWidthAndHeight() {
    Column {
        explain("1. Modifier의 크기(너비, 높이) 조절하기")
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .background(Color.White)
        ) {

            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .fillMaxSize()
                    .background(Color.Blue)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = "첫번째 Box 영역",
                    fontSize = 12.sp, // 글자 크기
                )
            }

            Box(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .background(Color.Blue)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = "첫번째 Box 영역",
                    fontSize = 12.sp, // 글자 크기
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Green)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = "두번째 Box 영역",
                    fontSize = 12.sp, // 글자 크기
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.2f)
                    .background(Color.Yellow)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = "세번째 Box 영역",
                    fontSize = 12.sp, // 글자 크기
                )
            }
        }
    }
}