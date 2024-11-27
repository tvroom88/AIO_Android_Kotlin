package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.home

import android.util.Log
import android.view.View
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoroutineTestViewModel @Inject constructor(
    private val getCoroutineTestUseCase: GetUseCase.GetCoroutineTestUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _successMsg = MutableLiveData<String>()
    val successMsg: LiveData<String> get() = _successMsg

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> get() = _errorMsg

    private val _numOfData = MutableLiveData<Int>()
    val numOfData: LiveData<Int> get() = _numOfData

    private val _coroutineTestData = MutableLiveData<List<CoroutineTest>>()
    val coroutineTestData: LiveData<List<CoroutineTest>> get() = _coroutineTestData

    private val _coroutineTestDataFromLocal = MutableLiveData<List<CoroutineTest>>()
    val coroutineTestDataFromLocal: LiveData<List<CoroutineTest>> get() = _coroutineTestDataFromLocal

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

    fun getCoroutineTestLocalData(): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val result = getCoroutineTestUseCase.getAllLocalData()
            result.onSuccess {
                _coroutineTestDataFromLocal.postValue(it)
                getNumOfDataInDb() // delete 완료후 다시 db에 들어있는 숫자를 센다.
            }.onFailure {
                _errorMsg.postValue(it.message)
            }
            _isLoading.postValue(false)
        }
    }

    /**
     * 데이터 넣기
     */
    fun insertCoroutineTestToLocal() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            if (_coroutineTestData.value != null) {
                val data = _coroutineTestData.value
                if (data!!.isNotEmpty()) {
                    Log.d("LocalData", "isNotEmpty")
                    Log.d("LocalData", "size : ${data.size}")

                    val result = getCoroutineTestUseCase.saveAllDataToLocal(data)
                    result.onSuccess {
                        _successMsg.value = "정상적으로 들어갔습니다."
                    }.onFailure { throwable -> _errorMsg.value = throwable.message }
                } else {
                    _errorMsg.value = "서버에서 데이터를 먼저 불러오세요"
                    Log.d("LocalData", "서버에서 데이터를 먼저 불러오세요")
                }
            } else {
                _errorMsg.value = "서버에서 데이터를 먼저 불러오세요"
                Log.d("LocalData", "서버에서 데이터를 먼저 불러오세요")
            }
            _isLoading.postValue(false)
        }
    }

    fun deleteAllCoroutineDataFromLocal() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getCoroutineTestUseCase.deleteAlLDataFromLocal()
            result.onSuccess {
                _successMsg.value = "정상적으로 삭제했습니다."
                getCoroutineTestLocalData() // 지우고 나서 데이터 지워진것도 업데이트
            }.onFailure { throwable -> _errorMsg.value = throwable.message }

            _isLoading.postValue(false)
        }
    }

    private suspend fun getNumOfDataInDb() {
        Log.d("viewmodel", "getNumOfDataInDb start")
        val result = getCoroutineTestUseCase.numOfDataInDb()
        result.onSuccess {
            _numOfData.postValue(it)
            Log.d("viewmodel", "getNumOfDataInDb - onSuccess")
        }.onFailure { throwable ->
            _errorMsg.postValue(throwable.message)
        }
        Log.d("viewmodel", "getNumOfDataInDb end")
    }
}