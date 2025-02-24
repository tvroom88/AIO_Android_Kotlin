package com.aio.kotlin.studylist.jetpack.datastore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.AioApplication
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DataStoreViewModel : ViewModel() {

    private val dataStoreUtil = AioApplication.getInstance().getDataStore()

    val dataStoreTestFlow: StateFlow<String> = dataStoreUtil.testString
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            ""
        )

    fun saveText(text: String) {
        viewModelScope.launch {
            dataStoreUtil.setText(text)
        }
    }

    val newDataTestFlow: StateFlow<ProtoDataStore> = dataStoreUtil.userPreferencesFlow
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            ProtoDataStore(false, "")
        )

    fun saveProtoDataStore(data: String) {
        Log.d("HereHere", "2. saveProtoDataStore - data : $data")

        viewModelScope.launch {
            dataStoreUtil.updateShowCompleted(true, data)
        }
    }

}