package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.PagingRepository
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local.PagingItemEntity
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.remote.PagingAlbumItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PagingViewModel(
    val repository: PagingRepository
) : ViewModel() {

    private val _items = MutableStateFlow<PagingData<PagingItemEntity>>(PagingData.empty())
    val items: StateFlow<PagingData<PagingItemEntity>> = _items

    init {
        refreshPaging()
    }

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
    val remotePagingFlow: Flow<PagingData<PagingAlbumItem>> =
        repository.getRemotePagingFlow()
            .cachedIn(viewModelScope)
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
