package com.aio.kotlin.studylist.jetpack.binding.databinding

import android.widget.MultiAutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aio.kotlin.R

class DataBindingViewModel : ViewModel() {

    // LiveData 변수 선언
    private val _basicText = MutableLiveData<String>()
    val basicText: LiveData<String> = _basicText

    var url = 0

    init {
        // 초기 데이터 설정
        _basicText.value = "Hello, DataBinding!"
        url = R.drawable.chick
    }

    fun resetText() {
        _basicText.value = ""
    }

    fun updateText(): MutableLiveData<String> {
        return _basicText
    }

}