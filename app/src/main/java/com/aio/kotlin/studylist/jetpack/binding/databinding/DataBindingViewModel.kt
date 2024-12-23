package com.aio.kotlin.studylist.jetpack.binding.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DataBindingViewModel : ViewModel() {

    // LiveData 변수 선언
    private val _basicText = MutableLiveData<String>()
    val basicText: LiveData<String> = _basicText

    private val _username = MutableStateFlow<String>("")
    val username: StateFlow<String> = _username

    var url = 0

    init {
        // 초기 데이터 설정
        _basicText.value = "Hello, DataBinding!"
        url = R.drawable.chick

        viewModelScope.launch {
            _username.value = loadUserName() // repository에서 가져왔다고 가정
        }
    }

    fun resetText() {
        _basicText.value = ""
    }

    fun updateText(): MutableLiveData<String> {
        return _basicText
    }

    private fun loadUserName(): String {
        return "Tom"
    }
}