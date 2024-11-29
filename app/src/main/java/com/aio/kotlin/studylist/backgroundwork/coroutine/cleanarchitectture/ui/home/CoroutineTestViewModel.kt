package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutinesTestState
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase.GetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoroutineTestViewModel @Inject constructor(
    private val getCoroutineTestUseCase: GetUseCase.GetCoroutineTestUseCase
) : ViewModel() {

    private val _numOfData = MutableLiveData<CoroutinesTestState<Int>>()
    val numOfData: LiveData<CoroutinesTestState<Int>> get() = _numOfData

    private val _coroutineTestData = MutableLiveData<CoroutinesTestState<List<CoroutineTest>>>()
    val coroutineTestData: LiveData<CoroutinesTestState<List<CoroutineTest>>> get() = _coroutineTestData

    private val _coroutineTestLocalData =
        MutableLiveData<CoroutinesTestState<List<CoroutineTest>>>()
    val coroutineTestLocalData: LiveData<CoroutinesTestState<List<CoroutineTest>>> get() = _coroutineTestLocalData

    fun getCoroutineTestData() = viewModelScope.launch(Dispatchers.IO) {
        _coroutineTestData.postValue(CoroutinesTestState.loading())
        val result = getCoroutineTestUseCase()
        result.onSuccess {
            _coroutineTestData.postValue(CoroutinesTestState.success(it))
        }.onFailure {
            _coroutineTestData.postValue(CoroutinesTestState.error(it.message.toString()))
        }
    }

    // Room DB로부터 데이터를 받아온다.
    fun getCoroutineTestLocalData() {
        _coroutineTestLocalData.postValue(CoroutinesTestState.loading())
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCoroutineTestUseCase.getAllLocalData()
            result.onSuccess {
                _coroutineTestLocalData.postValue(CoroutinesTestState.success(it))
            }.onFailure {
                _coroutineTestLocalData.postValue(CoroutinesTestState.error(it.message.toString()))
            }
        }
    }

    /**
     * 데이터 넣기
     * Room DB 숫자가 0이면 Remote에서 가져온다.
     */
    fun insertCoroutineTestToLocal() {
        _coroutineTestLocalData.postValue(CoroutinesTestState.loading())
        viewModelScope.launch {
            var remoteDataList: List<CoroutineTest> = mutableListOf()
            if (_coroutineTestData.value == null || (_coroutineTestData.value != null && _coroutineTestData.value!!.data == null)) { // 만약 _coroutineTestData가 비어있다면
                getCoroutineTestUseCase().onSuccess { data ->
                    remoteDataList = data
                }
            } else {
                remoteDataList = _coroutineTestData.value?.data!!
            }
            val result = getCoroutineTestUseCase.saveAllDataToLocal(remoteDataList)
            result.onSuccess { data ->
                _coroutineTestLocalData.postValue(CoroutinesTestState.success(data))
            }.onFailure { error ->
                _coroutineTestLocalData.postValue(CoroutinesTestState.error(error.message.toString()))
            }
        }
    }

    fun deleteAllCoroutineDataFromLocal() {
        _coroutineTestLocalData.postValue(CoroutinesTestState.loading())
        viewModelScope.launch {
            val result = getCoroutineTestUseCase.deleteAlLDataFromLocal()
            result.onSuccess {
                _coroutineTestLocalData.postValue(CoroutinesTestState.initialize())
            }.onFailure {
                _coroutineTestLocalData.postValue(CoroutinesTestState.error(it.message.toString()))
            }
        }
    }

    private suspend fun getNumOfDataInDb(callFromUI: Boolean) {
        if (callFromUI) _numOfData.postValue(CoroutinesTestState.loading()) // UI에서 직접 불러오면 로딩창 보여준다.
        val result = getCoroutineTestUseCase.numOfDataInDb()
        result.onSuccess {
            _numOfData.postValue(CoroutinesTestState.success(it))
        }.onFailure { throwable ->
            _numOfData.postValue(CoroutinesTestState.error(throwable.message.toString()))
        }
    }
}