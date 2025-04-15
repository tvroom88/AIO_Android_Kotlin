package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local.PagingItemEntity
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.remote.PagingAlbumItem

@Composable
fun PagingComposeScreen(pagingViewModel: PagingViewModel) {
    val localItem = pagingViewModel.items.collectAsLazyPagingItems()

    // 로컬 데이터가 완전히 로딩되었는지 확인
    val isLocalLoadComplete = remember(localItem.loadState) {
        localItem.loadState.refresh is LoadState.NotLoading &&
                localItem.loadState.append.endOfPaginationReached
    }

    LaunchedEffect(isLocalLoadComplete) {
        Log.d("TestTest", "isLocalLoadComplete : $isLocalLoadComplete")
        if (isLocalLoadComplete) {
            pagingViewModel.getRemotePagingFlow()
        }
    }

    // Remote Flow는 로컬 로딩이 끝났을 때만 collect
    val remoteFlow = remember(isLocalLoadComplete) {
        if (isLocalLoadComplete) pagingViewModel.remotePagingFlow else null
    }
    val remoteItem = remoteFlow?.collectAsState()?.value?.collectAsLazyPagingItems()


    val lazyListState = rememberLazyListState()

    // 로딩 상태 확인
    val isLoading = localItem.loadState.refresh is LoadState.Loading ||
            localItem.loadState.append is LoadState.Loading ||
            remoteItem?.loadState?.refresh is LoadState.Loading ||
            remoteItem?.loadState?.append is LoadState.Loading

    // 에러 상태 확인
    val isError = localItem.loadState.append is LoadState.Error ||
            remoteItem?.loadState?.append is LoadState.Error

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp),
                    onClick = { pagingViewModel.insertUser("TestTest") }
                ) {
                    Text(text = "Add")
                }

                Button(
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp),
                    onClick = { pagingViewModel.deleteAllUser() }
                ) {
                    Text(text = "Delete")
                }
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                state = lazyListState
            ) {

                // --- 1. 로컬 데이터 표시 ---
                items(localItem.itemCount) { idx ->
                    val item = localItem[idx]
                    if (item != null) {
                        ItemView(item)
                    } else {
                        Text("로딩 중...")
                    }
                }

                // --- 2. 로컬 다 받은 후 remote 데이터 표시 ---
                if (isLocalLoadComplete && remoteItem != null) {
                    items(remoteItem.itemCount) { idx ->
                        val item = remoteItem[idx]
                        if (item != null) {
                            RemoteItemView(item)
                        } else {
                            Text("원격 로딩 중...")
                        }
                    }

                }
            }
        }

        // 2. 중앙에 로딩 UI
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(enabled = false) {},
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        // 3. 에러 처리도 중앙에 표시 가능
        if (isError) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error loading data", color = Color.Red)
            }
        }
    }
}

@Composable
fun ItemView(item: PagingItemEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.LightGray.copy(alpha = 0.3f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Local: ${item.title ?: ""}")
    }
}

@Composable
fun RemoteItemView(item: PagingAlbumItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Blue.copy(alpha = 0.3f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Remote: ${item.title}")
    }
}
