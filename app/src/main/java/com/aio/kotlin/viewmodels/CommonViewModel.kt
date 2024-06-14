package com.aio.kotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CommonViewModel {

    // LiveData 변수 선언
    private val _baseLiveData = MutableLiveData<String>()
    val baseLiveData: LiveData<String> get() = _baseLiveData
}