package com.aio.kotlin.studylist.jetpack.compose.state.samguozhi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aio.kotlin.studylist.jetpack.compose.state.wellness.WellnessTaskItem

@Composable
fun SamguozhiScreen(
    modifier: Modifier = Modifier,
    samguozhiViewModel: SamguozhiViewModel = viewModel()
) {
    Row(modifier = modifier) {
        characterList(samguozhiViewModel.characterList, modifier.weight(1f))
        VerticalDivider(color = Color.DarkGray)
        selectedCharacterList(modifier.weight(1f))
    }
}

@Composable
fun characterList(characterList: List<CustomCharacter>, modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier.padding(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp), // 아이템 사이의 간격
        horizontalAlignment = Alignment.CenterHorizontally //  중앙 정렬 추가
    ) {
        items(items = characterList) { character ->
            Text(
                color = Color.Black,
                text = character.name, // text 영역 사이즈 설정
            )
        }
    }
}

@Composable
fun selectedCharacterList(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        color = Color.Green,
        text = "texttexttexttext"
    )
}

@Composable
@Preview()
fun previewSamguozhiScreen() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        SamguozhiScreen()
    }
}