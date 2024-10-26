package com.aio.kotlin.studylist.jetpack.compose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aio.kotlin.studylist.jetpack.compose.theme.DiverseComposeLayoutsTheme

/**
 * Column, Row, Box(= FrameLayout), BoxWithConstraints, ConstraintsLayout
 */
class ComposeALayouts : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiverseComposeLayoutsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Column {
                        AddColumn()
                        AddRow()
                        AddBox()
                        AddConstraintLayout()
                    }
                }

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AddColumn() {
        Column(
            modifier = Modifier
                .background(Color.Green)
        ) {
            Text(text = "1-1. Column ")
            Text(text = "1-2. Check where second text is with Column.")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AddRow() {
        Row(
            modifier = Modifier
                .background(Color.Blue)
        ) {
            Text(text = "2-1. Row ")
            Text(text = "2-2. Check where second text is with Row.")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AddBox() {
        Box(
            modifier = Modifier
                .fillMaxWidth() // 너비(width) 꽉 채우기
                .height(100.dp)
                .background(Color.Cyan)
        ) // 높이(height) 100dp 로 설정
        {
            Text(
                text = "3-1. Box",
                modifier = Modifier.align(Alignment.TopStart) // 상단 왼쪽 정렬
            )
            Text(
                text = "3-2. Check where second text is with Box.",
                modifier = Modifier.align(Alignment.BottomStart) // 하단 왼쪽 정렬
            )
        }
    }

    /**
     * Constraint Layout :
     * 위젯을 다른 위젯의 위치에 대해 상대적으로 배치시키는 레이아웃이다. 기존 xml에서 많이 활용되던 레이아웃인데 xml에서는
     * 성능상의 이점이 많았지만, Compose에서는 성능상의 이점은 없다. 따라서 Android 공식 사이트에서는 뷰가 복잡하거나 가독성이
     * 떨어질 때만 ConstraintLayout을 이용하는 것을 권장하고 있다.
     */

    @Preview(showBackground = true)
    @Composable
    fun AddConstraintLayout() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {// 레이아웃의 크기 조정) {
            val (button, text) = createRefs()
            Button(
                onClick = { },
                modifier = Modifier.constrainAs(button) {
                    // DO SOMETHING
                    top.linkTo(parent.top, margin = 16.dp) // 부모의 top에 16dp 마진을 준다
                    start.linkTo(parent.start) // 부모의 start에 맞춘다 (왼쪽 정렬)
                }
            ) {
                Text("Button")
            }
            Text(
                "Text",
                modifier = Modifier.constrainAs(text) {
                    // DO SOMETHING
                    top.linkTo(button.bottom, margin = 16.dp) // 버튼의 bottom에 16dp 마진을 준다
                    start.linkTo(parent.start) // 부모의 start에 맞춘다 (왼쪽 정렬)
                    end.linkTo(parent.end)
                }
            )
        }
    }

}






































