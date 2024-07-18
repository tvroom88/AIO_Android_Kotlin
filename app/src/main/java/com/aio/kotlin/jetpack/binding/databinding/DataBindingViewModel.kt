package com.aio.kotlin.jetpack.binding.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindingViewModel : ViewModel() {

    // LiveData 변수 선언
    private val _someLiveData = MutableLiveData<String>()
    val someLiveData: LiveData<String> get() = _someLiveData

    init {
        // 초기 데이터 설정
        _someLiveData.value = "Hello, DataBinding!"
    }
}