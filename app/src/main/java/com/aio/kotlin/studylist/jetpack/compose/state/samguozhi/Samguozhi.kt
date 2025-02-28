package com.aio.kotlin.studylist.jetpack.compose.state.samguozhi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SamguozhiScreen(
    modifier: Modifier = Modifier,
    samguozhiViewModel: SamguozhiViewModel = viewModel()
) {

    Row(modifier = modifier) {
        val characterList by samguozhiViewModel.characterList.collectAsStateWithLifecycle()
        val selectedList by samguozhiViewModel.selectedList.collectAsStateWithLifecycle()

        characterList(
            characterList = characterList,
            task = { item, checked, idx->
                samguozhiViewModel.changeCustomCharacter(item, checked, idx)
            },
            modifier = modifier.weight(1f)
        )
        VerticalDivider(color = Color.DarkGray)
        selectedCharacterList(
            selectedList = selectedList,
            task = { item, idx ->
                samguozhiViewModel.removeSelectedCharacter(item, idx, false)
            },
            modifier = modifier.weight(1f)
        )
    }
}

@Composable
fun characterList(
    characterList: List<CustomCharacter>,
    task: (CustomCharacter, Boolean, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp), // 아이템 사이의 간격
        horizontalAlignment = Alignment.CenterHorizontally //  중앙 정렬 추가
    ) {
        itemsIndexed(
            items = characterList,
        ) { index, character ->
            characterListItem(
                characterName = character.name,
                checked = character.checked,
                onCheckedChange = { checked -> task(character, checked, index) },
                onDelete = {},
                false
            )
        }
    }
}

@Composable
fun characterListItem(
    characterName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = characterName
        )

        if (!isFavorite) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        } else {
            IconButton(onClick = onDelete) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
    }
}


@Composable
fun selectedCharacterList(
    selectedList: List<CustomCharacter>,
    task: (CustomCharacter, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp), // 아이템 사이의 간격
        horizontalAlignment = Alignment.CenterHorizontally //  중앙 정렬 추가
    ) {
        itemsIndexed(items = selectedList) { index, character  ->
            characterListItem(
                characterName = character.name,
                checked = character.checked,
                onCheckedChange = { },
                onDelete = { task(character, index) },
                true
            )
        }
    }
}

@Composable
@Preview()
fun previewSamguozhiScreen() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        SamguozhiScreen()
    }
}