package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository.CoroutineTestRepository
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineComment
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutinesTestState
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase.GetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoroutineTestViewModel @Inject constructor(
    private val getCoroutineTestUseCase: GetUseCase.GetCoroutineTestUseCase,
    private val coroutineTestRepository: CoroutineTestRepository
) : ViewModel() {

    // LiveData 사용
    private val _numOfData = MutableLiveData<CoroutinesTestState<Int>>()
    val numOfData: LiveData<CoroutinesTestState<Int>> get() = _numOfData

    private val _coroutineTestData = MutableLiveData<CoroutinesTestState<List<CoroutineTest>>>()
    val coroutineTestData: LiveData<CoroutinesTestState<List<CoroutineTest>>> get() = _coroutineTestData

    private val _coroutineTestLocalData =
        MutableLiveData<CoroutinesTestState<List<CoroutineTest>>>()
    val coroutineTestLocalData: LiveData<CoroutinesTestState<List<CoroutineTest>>> get() = _coroutineTestLocalData

    // StateFlow 데이터
    private val _coroutineCommentData: MutableStateFlow<CoroutinesTestState<List<CoroutineComment>>> =
        MutableStateFlow(CoroutinesTestState.initialize())
    val coroutineCommentData = _coroutineCommentData.asStateFlow()


    // Retrofit으로 데이터 가져오는 부분
    fun getCoroutineTestData() = viewModelScope.launch(Dispatchers.IO) {
        _coroutineTestData.postValue(CoroutinesTestState.loading())
        val result = getCoroutineTestUseCase()
        result.onSuccess {
            _coroutineTestData.postValue(CoroutinesTestState.success(it))
        }.onFailure {
            _coroutineTestData.postValue(CoroutinesTestState.error(it.message.toString()))
        }
    }

    fun getCoroutineCommentData() {
        _coroutineCommentData.value = CoroutinesTestState.loading()
        viewModelScope.launch {
            getCoroutineTestUseCase.getCoroutineCommentData().collect { result ->
                result.onSuccess {
                    _coroutineCommentData.value = CoroutinesTestState.success(it)
                }.onFailure {
                    _coroutineCommentData.value = CoroutinesTestState.error(it.message.toString())
                }
            }
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
            val result = getCoroutineTestUseCase.deleteAllDataFromLocal()
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


    private val _coroutineCommentDataFromLocal: MutableStateFlow<CoroutinesTestState<List<CoroutineComment>>> =
        MutableStateFlow(CoroutinesTestState.initialize())

    val coroutineCommentDataFromLocal = _coroutineCommentDataFromLocal.asStateFlow()

    fun getAllCommentData() {
        _coroutineCommentDataFromLocal.value = CoroutinesTestState.loading()
        viewModelScope.launch {
            val result = coroutineTestRepository.getAllCommentFromLocal()
            result.onSuccess { flowData ->
                flowData.collect { data ->
                    _coroutineCommentDataFromLocal.value = CoroutinesTestState.success(data)
                }
            }.onFailure { err ->
                _coroutineCommentDataFromLocal.value = CoroutinesTestState.error(err.toString())
            }
        }
    }

    fun insertCoroutineCommentToLocal() {
        viewModelScope.launch {
            if (!_coroutineCommentData.value.data.isNullOrEmpty()) {
                val result = coroutineTestRepository.insertAllCommentToLocal(_coroutineCommentData.value.data!!)
                result.onSuccess {
                    data -> _coroutineCommentDataFromLocal.value = CoroutinesTestState.success(data)
                    Log.d("CoroutineTestViewModel", "insertCoroutineCommentToLocal - onSuccess")
                }.onFailure { err ->
                    _coroutineCommentDataFromLocal.value = CoroutinesTestState.error(err.toString())
                    Log.d("CoroutineTestViewModel", "insertCoroutineCommentToLocal - onFailure")
                }
            }
        }
    }

    fun deleteAllCoroutineCommentDataFromLocal() {
        _coroutineTestLocalData.postValue(CoroutinesTestState.loading())
        viewModelScope.launch {
            val result = getCoroutineTestUseCase.deleteAllCommentDataFromLocal()
            result.onSuccess {
                _coroutineTestLocalData.postValue(CoroutinesTestState.initialize())
                Log.d("CoroutineTestViewModel", "deleteAllCoroutineCommentDataFromLocal - onSuccess")

            }.onFailure {
                _coroutineTestLocalData.postValue(CoroutinesTestState.error(it.message.toString()))
                Log.d("CoroutineTestViewModel", "deleteAllCoroutineCommentDataFromLocal - onFailure")

            }
        }
    }
}
