package com.aio.kotlin.studylist.jetpack.compose.state.samguozhi

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.aio.kotlin.studylist.jetpack.compose.state.wellness.WellnessTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SamguozhiViewModel : ViewModel() {

    private val _characterList = MutableStateFlow(getCharacterList())
    val characterList: StateFlow<List<CustomCharacter>> = _characterList.asStateFlow()

    private val _selectedList = MutableStateFlow<List<CustomCharacter>>(emptyList())
    val selectedList: StateFlow<List<CustomCharacter>> = _selectedList.asStateFlow()


    fun changeCustomCharacter(item: CustomCharacter, checked: Boolean, idx: Int) {

        _characterList.value = _characterList.value.mapIndexed { i, character ->
            if (i == idx) character.copy(
                checked = checked
            ) else character
        }

        if (checked) {
            // 체크가 되었고, 선택 리스트에 없으면 추가
            addSelectedCharacter(item)
        } else {
            // 체크가 해제되었고, 선택 리스트에 있으면 제거
            removeSelectedCharacter(item, idx, checked)
        }
    }

    private fun addSelectedCharacter(item: CustomCharacter) {
        _selectedList.value += item
        Log.d("TestTest", "add - ${_selectedList.value}")
    }

    fun removeSelectedCharacter(item: CustomCharacter, idx: Int,  checked: Boolean) {
        // 선택 리스트에서 제거
        _selectedList.value = _selectedList.value.filterNot { it.id == item.id }

        // characterList의 체크 상태도 false로 변경
        _characterList.value = _characterList.value.map { character ->
            if (character.id == item.id) character.copy(checked = false) else character
        }
    }
}

fun getCharacterList() = listOf(
    CustomCharacter(0, "조조", false),
    CustomCharacter(1, "유비", false),
    CustomCharacter(2, "손권", false),
    CustomCharacter(3, "제갈량", false),
    CustomCharacter(4, "여포", false),
    CustomCharacter(5, "관우", false),
    CustomCharacter(6, "하후돈", false),
    CustomCharacter(7, "서황", false),
    CustomCharacter(8, "주유", false),
    CustomCharacter(9, "육손", false),
)
