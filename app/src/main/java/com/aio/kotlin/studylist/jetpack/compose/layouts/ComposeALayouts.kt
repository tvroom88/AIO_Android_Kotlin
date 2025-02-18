package com.aio.kotlin.studylist.jetpack.compose.layouts

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aio.kotlin.R
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
                        Row()
                        Column()
                        CustomBox()
                    }
                }
            }
        }
    }


    // TextView
    @Composable
    fun customText() {
        Text(
            text = stringResource(R.string.first_text), // Text 글자
            modifier = Modifier
                .size(width = 80.dp, height = 100.dp) // text 영역 사이즈 설정
                .padding(start = 10.dp, bottom = 15.dp) // padding 설정
                .clickable(onClick = { Log.d("TestActivity", "click") }),
            color = Color.Green, // 색상 설정
            fontWeight = FontWeight.Bold, // font 굵기
            fontStyle = FontStyle.Italic, // font 스타일
            fontSize = 12.sp, // 글자 크기
            textAlign = TextAlign.Center, // Text 위치
            letterSpacing = 2.sp, // 글자 간격
            lineHeight = 28.sp, // 줄 간격
            textDecoration = TextDecoration.Underline, // 밑줄 효과
            overflow = TextOverflow.Ellipsis, // 길면 ... 표시
            softWrap = true // 자동 줄바꿈 여부
        )
    }

    // EditText
    @Composable
    fun customTextField(modifier: Modifier = Modifier) {
        var textField by rememberSaveable { mutableStateOf("") }
        TextField(
            value = textField, // 현재 입력된 값
            onValueChange = { textField = it }, // 값이 변경될 때 호출되는 콜백
            leadingIcon = {  // 입력창 왼쪽에 나오는 아이콘
                Icon(
                    imageVector = Icons.Default.Search, // 기본 검색 아이콘 사용
                    contentDescription = null // 접근성 설명 (null로 두면 스크린 리더에서 무시)
                )
            },
            colors = TextFieldDefaults.colors( // TextField의 색상 지정
                unfocusedContainerColor = MaterialTheme.colorScheme.surface, // 포커스되지 않았을 때 배경색
                focusedContainerColor = MaterialTheme.colorScheme.surface // 포커스되었을 때 배경색
            ),
            placeholder = { // 힌트 텍스트 (아무것도 입력되지 않았을 때 표시)
                Text("Search")
            },
            modifier = modifier
                .fillMaxWidth() // 너비를 부모 크기에 맞게 설정
                .heightIn(min = 56.dp), // 최소 높이 설정
            visualTransformation = PasswordVisualTransformation(), // 입력된 값을 비밀번호 형태(●●●)로 변환
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // 키보드 타입을 비밀번호 입력용으로 설정
        )
    }

    // ImageView
    @Composable
    fun customImage() {
        val borderWidth = 4.dp
        Row(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 10.dp
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.aio_android),
                contentDescription = stringResource(id = R.string.first_text),
                modifier = Modifier
                    .size(150.dp)
            )

            AsyncImage(
                model = "https://cdn.news.hidoc.co.kr/image/logo/favicon.ico",
                contentDescription = "Translated description of what the image contains",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }

    @Composable
    fun Row() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            customText()
            customTextField(Modifier.padding(horizontal = 16.dp))
            customImage()
        }
    }

    @Composable
    fun Column() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            customText()
            customTextField(Modifier.padding(horizontal = 16.dp))
            customImage()
        }
    }

    @Composable
    fun CustomBox() {
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .background(color = Color.Cyan)
                .size(400.dp, 300.dp)
        ) {
            Text(modifier = Modifier.align(Alignment.TopStart), text = "First")
            Text(modifier = Modifier.align(Alignment.Center), text = "Second")
            Text(text = "Third")
        }
    }

    /**
     * LazyColumn, LazyRow, LazyGrid
     */
    @Composable
    fun recyclerView() {
        val data = arrayOf("1", "2", "3", "4", "5")
//    LazyRow(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp),
//        modifier = modifier
//    ) {
//        items(alignYourBodyData) { item ->
//            AlignYourBodyElement(item.drawable, item.text)
//        }
//    }
    }

    @Composable
    fun Grid() {

    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, widthDp = 100, heightDp = 200)
    @Composable
    fun PreviewRowColumn() {
        DiverseComposeLayoutsTheme {
            Column {
                Row()
                Column()
                CustomBox()
            }
        }
    }


    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun PreviewText() {
        DiverseComposeLayoutsTheme { customText() }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun PreviewTextField() {
        DiverseComposeLayoutsTheme { customTextField() }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun PreviewImage() {
        DiverseComposeLayoutsTheme { customImage() }
    }

}
