package com.aio.kotlin.studylist.jetpack.compose.state.samguozhi

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.aio.kotlin.studylist.jetpack.compose.state.wellness.WellnessTask

class SamguozhiViewModel : ViewModel() {

    private val _characterList = getCharacterList()
    val characterList: List<CustomCharacter>
        get() = _characterList

    private val _selectedList = mutableListOf<CustomCharacter>()
    val selectedList: List<CustomCharacter>
        get() = _selectedList

}


fun getCharacterList() = listOf(
    CustomCharacter(0, "조조"),
    CustomCharacter(1, "유비"),
    CustomCharacter(2, "손권"),
    CustomCharacter(3, "제갈량"),
    CustomCharacter(4, "여포"),
    CustomCharacter(5, "관우"),
    CustomCharacter(6, "하후돈"),
    CustomCharacter(7, "서황"),
    CustomCharacter(8, "주유"),
    CustomCharacter(9, "육손"),
)
