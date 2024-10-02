package com.aio.kotlin.studylist.architecturepattern

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MvvmSimpleViewModel : ViewModel() {

    // LiveData 변수 선언
    private val _someLiveData = MutableLiveData<Int>()
    val someLiveData: LiveData<Int> get() = _someLiveData

    init {
        Log.d("MvvmSimpleViewModel", "MvvmSimpleViewModel - init")
        if(_someLiveData.value == null){
            Log.d("MvvmSimpleViewModel", "MvvmSimpleViewModel - _someLiveData.value is null")
            _someLiveData.value = 0
        }
    }

    fun plus() {
        Log.d("MvvmSimpleViewModel", "MvvmSimpleViewModel - plus")
        _someLiveData.value = _someLiveData.value?.plus(1)
    }

    fun minus() {
        Log.d("MvvmSimpleViewModel", "MvvmSimpleViewModel - minus")
        _someLiveData.value = _someLiveData.value?.minus(1)
    }
}