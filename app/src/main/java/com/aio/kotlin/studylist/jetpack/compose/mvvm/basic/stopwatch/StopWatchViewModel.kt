package com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.stopwatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.composeBasicUser.ComposeBasicUser
import com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.composeBasicUser.ComposeBasicUserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StopWatchViewModel : ViewModel() {

    private val _timeInMillis = MutableStateFlow(0L)
    val timeInMillis: StateFlow<Long> = _timeInMillis.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    fun startStopWatch() {
        if (_isRunning.value) return

        _isRunning.value = true
        val startTime = System.currentTimeMillis() - _timeInMillis.value

        viewModelScope.launch {
            while (_isRunning.value) {
                _timeInMillis.value = System.currentTimeMillis() - startTime
                delay(10L)
            }
        }
    }

    fun pauseStopWatch() {
        _isRunning.value = false
    }

    fun resetStopWatch() {
        _isRunning.value = false
        _timeInMillis.value = 0L
    }


}
