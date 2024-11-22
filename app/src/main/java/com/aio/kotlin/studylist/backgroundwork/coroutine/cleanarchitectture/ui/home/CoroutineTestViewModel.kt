package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutinesTestState
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase.GetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoroutineTestViewModel @Inject constructor(
    private val getCoroutineTestUseCase: GetUseCase.GetCoroutineTestUseCase
) : ViewModel() {

    private val _coroutineTestData = MutableLiveData<CoroutinesTestState<List<CoroutineTest>>>()
    val coroutineTestData: LiveData<CoroutinesTestState<List<CoroutineTest>>> get() = _coroutineTestData

    fun getCoroutineTestData() = viewModelScope.launch {
        _coroutineTestData.value = CoroutinesTestState.loading()
        val currentThread = Thread.currentThread()
        Log.d("currentThread", currentThread.name)

        _coroutineTestData.postValue(getCoroutineTestUseCase())
    }
}