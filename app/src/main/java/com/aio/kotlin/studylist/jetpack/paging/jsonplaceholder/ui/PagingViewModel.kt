package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.PagingRepository
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local.PagingItemEntity
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.remote.PagingAlbumItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PagingViewModel(
    val repository: PagingRepository
) : ViewModel() {

    private val _items = MutableStateFlow<PagingData<PagingItemEntity>>(PagingData.empty())
    val items: StateFlow<PagingData<PagingItemEntity>> = _items

    init {
        refreshPaging()
    }

    // UI 까지 업데이트 됨
    fun insertUser(myTitle: String) {
        viewModelScope.launch {
            repository.insertUser(PagingItemEntity(title = myTitle))
            delay(100)
            refreshPaging()
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch {
            repository.deleteAll()
            delay(100)
            refreshPaging()
        }
    }

    private fun refreshPaging() {
        viewModelScope.launch {
            repository.getItemPager()
                .cachedIn(viewModelScope)
                .collect {
                    _items.value = it
                }
        }
    }

    // --- Remote ---
    // Retrofit 기반 Remote Paging Flow
    private val _remotePagingFlow = MutableStateFlow<Flow<PagingData<PagingAlbumItem>>?>(null)
    val remotePagingFlow: StateFlow<Flow<PagingData<PagingAlbumItem>>?> = _remotePagingFlow

    fun getRemotePagingFlow() {
        viewModelScope.launch {
            val startId = repository.getLastLocalId() // suspend 함수라 이렇게 받아야 해
            _remotePagingFlow.value = repository.getRemotePagingFlow(startId)
                .map { pagingData ->
                    pagingData.map { item ->
                        repository.insertUser(
                            PagingItemEntity(
                                id = item.id,
                                userId = item.userId,
                                title = item.title,
                            )
                        )
                        item
                    }
                }
                .cachedIn(viewModelScope)
        }
    }
}

class PagingViewModelFactory(
    private val repository: PagingRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PagingViewModel::class.java)) {
            return PagingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
