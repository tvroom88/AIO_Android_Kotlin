package com.aio.kotlin.studylist.jetpack.compose.modifier

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * 1. 기본적인 상태 기반 애니메이션 (animate*AsState)
 * 2. 가시성 애니메이션 (AnimatedVisibility)
 * 3. 반복 애니메이션 (rememberInfiniteTransition)
 * 4. 터치 입력 애니메이션 (pointerInput + detectDragGestures)
 * 5. 다중 애니메이션 조합 (scale + rotate)
 * 6. 화면 전환 애니메이션 (AnimatedContent)
 *
 * https://jige.tistory.com/76
 */

@Composable
fun animationState() {
    var expanded by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (expanded) 1.5f else 1f,
        animationSpec = tween(durationMillis = 500),
        label = "ScaleAnimation"
    )

    Box(
        modifier = Modifier
            .offset(
                x = (100.dp * (scale - 1) / 2), // 오른쪽 이동 보정
                y = (100.dp * (scale - 1))     // 아래쪽 이동 보정
            )
            .size(100.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                transformOrigin = TransformOrigin(0.5f, 1f) // 아래쪽 기준으로 확대
            }
            .background(Color.Red, shape = RectangleShape)
            .clickable { expanded = !expanded }
    )
}

@Composable
fun animationVisibility() {
    var isVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .width(100.dp)
            .background(Color.Green, shape = RectangleShape)
            .clickable {
                isVisible = !isVisible
            }
    ) {
        Text("Text입니다.")
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Yellow, shape = RectangleShape)
            )
        }
    }
}

@Composable
fun ExpandableBox() {
    var isExpanded by remember { mutableStateOf(false) }
    val size by animateDpAsState(targetValue = if (isExpanded) 200.dp else 100.dp, label = "")

    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Blue)
            .clickable { isExpanded = !isExpanded },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "클릭!", color = Color.White, fontSize = 16.sp)
    }
}

@Preview
@Composable
fun AnimationScreen() {
    Column{
        explain("5. 애니매이션")
        animationState()
        Spacer(modifier = Modifier.height(12.dp)) // 위쪽 마진 효과
        animationVisibility()
        Spacer(modifier = Modifier.height(12.dp)) // 위쪽 마진 효과
        ExpandableBox()
    }
}