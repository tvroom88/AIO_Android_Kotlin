package com.aio.kotlin.studylist.jetpack.compose.modifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aio.kotlin.studylist.jetpack.compose.theme.DiverseComposeLayoutsTheme

/**
 * url : https://kotlinworld.com/191
 *
 * UI 구성요소들을 꾸미기 위한 Modifier
 *  1. 크기 조정
 *  2. 정렬 및 배치
 *  3. 배경 및 스타일링
 *
 *  4. 클릭 이벤트
 *  .clickable { /* 클릭 이벤트 */ }
 * .pointerInput(Unit) { detectTapGestures(onTap = { /* 탭 이벤트 */ }) }
 *
 *  5. 애니매이션 효과
 * .animateContentSize(): 크기 변경 시 애니메이션 적용
 * .graphicsLayer { rotationZ = 45f }: 그래픽 변형
 */
class ComposeModifier : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiverseComposeLayoutsTheme {

                val scrollState = rememberScrollState()

                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.background(color = Color.White),

                    ) {
                    Column(modifier = Modifier.verticalScroll(scrollState)) {
                        mWidthAndHeight()
                        Spacer(modifier = Modifier.height(5.dp))

                        mPaddingAndOffset()
                        Spacer(modifier = Modifier.height(5.dp))

                        BackgroundScreen()
                        Spacer(modifier = Modifier.height(5.dp))

                        ClickEventScreen()
                        Spacer(modifier = Modifier.height(5.dp))

                        AnimationScreen()
                        Spacer(modifier = Modifier.height(50.dp))
                    }

                }
            }
        }
    }
}

@Composable
fun ModifierScreen(){
    val scrollState = rememberScrollState()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.background(color = Color.White),

        ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            mWidthAndHeight()
            Spacer(modifier = Modifier.height(5.dp))

            mPaddingAndOffset()
            Spacer(modifier = Modifier.height(5.dp))

            BackgroundScreen()
            Spacer(modifier = Modifier.height(5.dp))

            ClickEventScreen()
            Spacer(modifier = Modifier.height(5.dp))

            AnimationScreen()
            Spacer(modifier = Modifier.height(50.dp))
        }

    }
}

@Composable
fun explain(title: String) {
    Spacer(modifier = Modifier.height(12.dp)) // 위쪽 마진 효과
    Text(
        text = title, fontSize = 14.sp
    )
    Spacer(modifier = Modifier.height(12.dp)) // 위쪽 마진 효과
}

@Preview
@Composable
fun Test() {
    mWidthAndHeight()
}

@Preview
@Composable
fun Test2() {
    mPaddingAndOffset()
}

@Preview
@Composable
fun Test3() {
    BackgroundScreen()
}

@Preview
@Composable
fun Test4() {

}
