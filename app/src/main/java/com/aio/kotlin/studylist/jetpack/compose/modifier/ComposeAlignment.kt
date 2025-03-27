package com.aio.kotlin.studylist.jetpack.compose.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * 2. padding, offset 이용하여 컴포넌트간 여유공간 만들기
 * - padding은 자신을 기준으로 안쪽으로 얼만큼 공간을 비워놓을건지 결정하는 값이다.
 *
 * fun Modifier.padding
 *    (start: Dp = 0.dp, top: Dp = 0.dp, end: Dp = 0.dp, bottom: Dp = 0.dp)
 *    (horizontal: Dp = 0.dp, vertical: Dp = 0.dp)
 *    (all: Dp)
 *
 * Padding은 자식 컴포넌트의 크기도 줄인다.
 * offset : 측정값이 변경하지 않는다. 자식 컴포넌트가 줄어지지 않는다. padding은 크기에 따라 작아짐.
 *
 * fun Modifier.offset(x: Dp = 0.dp, y: Dp = 0.dp)
 */
@Composable
fun mPaddingAndOffset() {
    Column {
        explain("2. padding, offset 이용하여 컴포넌트간 여유공간 만들기")
        Row(
            Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Color.White)
                .padding(30.dp)
                .offset(x = 10.dp, y = 10.dp)
        ) {
            Box(
                Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .background(Color.Blue)
            )
        }
    }
}

