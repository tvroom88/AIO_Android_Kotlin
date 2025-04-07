package com.aio.kotlin.studylist.backgroundwork.coroutine.stateflow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoroutineStateFlowViewModel : ViewModel() {

    init {
        Log.d("CoroutineStateFlowViewModel", "CoroutineStateFlowViewModel")
    }

    // -------------------------------- StateFlow --------------------------------
    private val _coroutineData = MutableStateFlow(CoroutineUiState.success(""))
    val coroutineData: StateFlow<CoroutineUiState<String>> = _coroutineData.asStateFlow()

    private val _number = MutableStateFlow(0)

    // Value 프로퍼티 사용
    fun loadDataWithValue() {
        viewModelScope.launch {
            try {
                _coroutineData.value = CoroutineUiState.loading()
                delay(1000) // 서버에서 데이터를 가져온다는 시간을 가상으로
                val data = "Data With Value ${_number.value++}" // 실제 데이터 가져오기
                _coroutineData.value = CoroutineUiState.success(data)

            } catch (e: Exception) {
                _coroutineData.value = CoroutineUiState.error(e.message.toString()) // 에러 상태
            }
        }
    }

    //  emit() 함수 사용
    fun loadDataWithEmit() {
        viewModelScope.launch {
            try {
                _coroutineData.emit(CoroutineUiState.loading())
                delay(1000) // 서버에서 데이터를 가져온다는 시간을 가상으로
                val data = "Data With Value ${_number.value++}" // 실제 데이터 가져오기
                _coroutineData.emit(CoroutineUiState.success(data))
            } catch (e: Exception) {
                _coroutineData.emit(CoroutineUiState.error(e.message.toString()))
            }
        }
    }

    // -------------------------------- End StateFlow --------------------------------


    // -------------------------------- SharedFlow  --------------------------------
    private val _sharedFlow = MutableSharedFlow<String>(
        replay = 1, // (새로운 구독자들에게 이전 이벤트 방출 여부 (0: 방출X, 1 = 방출O)
        extraBufferCapacity = 1, // 추가 버터 생성 여부 (1 = 생성)
        onBufferOverflow = BufferOverflow.DROP_OLDEST // 버퍼 초과시 처리 여부
    )

    val sharedFlow = _sharedFlow.asSharedFlow()

    fun loadSharedFlow() {
        viewModelScope.launch {
            try {
                _sharedFlow.emit("로딩중")
                delay(1000) // 서버에서 데이터를 가져온다는 시간을 가상으로
                val data = "Data With Value ${_number.value++}" // 실제 데이터 가져오기
                _sharedFlow.emit(data)
            } catch (e: Exception) {
                _sharedFlow.emit("error : $e")
            }
        }
    }

    // -------------------------------- End SharedFlow  --------------------------------

    /**
     * StateIn이나 ShareIn을 사용하는 이유는 아마도 Flow를 갖고와서 바로 StateFlow와 SharedFlow로 변경해주기 인것 같다.
     * 그렇게 쓸게 아니면 굳이 이렇게 할 필요 없고 그냥 emit으로 값을 넘기고 하면 되기 때문이다.
     */


    fun makeSharedFlowData(data: String): Flow<String> = flow {
        emit("Loading")
        delay(1000)
        emit("Success : my data : $data")
    }

    // StateFlow + StateIn
    val stateFlowWithStateIn: StateFlow<String> = makeStateFlowData()
        .stateIn(
            scope = viewModelScope, // CoroutineScope
            started = SharingStarted.WhileSubscribed(2000),  // Flow 시작 시점 설정
            initialValue = "Initial State" // 초기 값
        )

    fun makeStateFlowData(): Flow<String> = flow {
        delay(1000)
        emit("Loading")
        delay(1000)
        emit("Success : my data : ${getData("statein")}")
    }

    private fun getData(data: String) :String{
        return data
    }

    //  SharedFlow + StateIn
    val sharedFlowWithSharedIn: SharedFlow<String> = makeStateFlowData().shareIn(
        scope = viewModelScope,  // viewModelScope로 생명주기 관리
        started = SharingStarted.WhileSubscribed(2000), // Flow 시작 시점 설정
        replay = 1  // 최근 값 1개 재생
    )

}