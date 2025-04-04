package com.aio.kotlin.studylist.jetpack.compose.mvvm.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.composeBasicUser.ComposeBasicUserScreen
import com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.composeBasicUser.ComposeBasicUserViewModel
import com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.stopwatch.StopWatchApp
import com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.stopwatch.StopWatchViewModel

/**
 * MVVM 방식의 여러가지 예제 모음
 * 1. StopWatch (Compose + ViewModel)
 * 2.

 * ViewModel에서는 데이터를 어떻게 관리하는지 중점적으로 보기
 *
 * 1. Flow (MutableStateFlow - collectAsState)로 사용
 * 2. LiveData (MutableLiveData - )
 * 3. State<T> (MutableState)
 *
 */
@Composable
fun BasicMvvmScreen(
    stopWatchViewModel: StopWatchViewModel,
    composeBasicUserViewModel: ComposeBasicUserViewModel
) {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        StopWatchApp(stopWatchViewModel)
        Spacer(modifier = Modifier.height(16.dp))
        ComposeBasicUserScreen(composeBasicUserViewModel)
    }

}

