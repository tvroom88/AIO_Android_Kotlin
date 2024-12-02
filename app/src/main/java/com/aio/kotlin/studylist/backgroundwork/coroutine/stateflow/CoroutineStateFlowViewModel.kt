package com.aio.kotlin.studylist.backgroundwork.coroutine.stateflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoroutineStateFlowViewModel : ViewModel() {

    // StateFlow
    private val _coroutineData = MutableStateFlow(CoroutineUiState.success(""))
    val coroutineData: StateFlow<CoroutineUiState<String>> = _coroutineData.asStateFlow()

    // ShareFlow
    private val _sharedFlow = MutableSharedFlow<String>(
        replay = 1,
        extraBufferCapacity = 1
    )

    val sharedFlow: SharedFlow<String> = _sharedFlow
        .shareIn(
            scope = viewModelScope,  // viewModelScope로 생명주기 관리
            started = SharingStarted.WhileSubscribed(1000), // 구독자가 없을 때 5초 후 정지
            replay = 1  // 최근 값 1개 재생
        )

    // StateIn
    private val _stateInFlow:StateFlow<String> = makeFlowData().stateIn(
        scope = viewModelScope,           // CoroutineScope
        started = SharingStarted.WhileSubscribed(1000),  // Flow 시작 시점 설정
        initialValue = "Initial State" // 초기 값
    )

    val stateInFlow: StateFlow<String> = _stateInFlow

    fun loadData() {
        viewModelScope.launch {
            try {
                _coroutineData.value = CoroutineUiState.loading()
                val data = makeFlowData() // 실제 데이터 가져오기
                data.collect { result ->
                    _coroutineData.value = CoroutineUiState.success(result)
                }
            } catch (e: Exception) {
                _coroutineData.value = CoroutineUiState.error(e.message.toString()) // 에러 상태
            }
        }

    }

    fun emitSharedFlowData() {
        viewModelScope.launch {
            _sharedFlow.emit("emit ShardFlowData")
        }
    }

    private fun makeFlowData(): Flow<String> = flow {
        delay(1000) // 서버에서 데이터를 가져온다는 시간을 가상으로
        emit("Get Data From Server Successly")
    }
}