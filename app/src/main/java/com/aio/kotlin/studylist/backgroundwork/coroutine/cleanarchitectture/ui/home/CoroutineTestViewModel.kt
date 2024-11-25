package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutineStatus
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutinesTestState
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase.GetUseCase
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoroutineTestViewModel @Inject constructor(
    private val getCoroutineTestUseCase: GetUseCase.GetCoroutineTestUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> get() = _errorMsg

    private val _coroutineTestData = MutableLiveData<List<CoroutineTest>>()
    val coroutineTestData: LiveData<List<CoroutineTest>> get() = _coroutineTestData

    fun getCoroutineTestData() = viewModelScope.launch(Dispatchers.IO) {

        _isLoading.postValue(true)
        with(getCoroutineTestUseCase()) {
            when (status) {

                CoroutineStatus.SUCCESS -> {
                    data?.let {
                        _coroutineTestData.postValue(it)
                    }
                }

                CoroutineStatus.ERROR -> {
                    message?.let {
                        _errorMsg.postValue(it)
                    }

                }
            }
            _isLoading.postValue(false)
        }
    }
}