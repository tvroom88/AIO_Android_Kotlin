package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui

sealed class UiState<out T> {
    data object INIT : UiState<Nothing>() // 초기 상태
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T): UiState<T>()
    data class Error(val message: String): UiState<Nothing>()
}