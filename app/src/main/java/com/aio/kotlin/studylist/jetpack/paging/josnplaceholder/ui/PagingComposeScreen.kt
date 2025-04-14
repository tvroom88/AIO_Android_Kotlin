package com.aio.kotlin.studylist.jetpack.paging.josnplaceholder.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.aio.kotlin.studylist.jetpack.paging.github.ui.progressIndicator
import com.aio.kotlin.studylist.jetpack.paging.josnplaceholder.data.local.PagingItemEntity
import kotlinx.coroutines.launch

@Composable
fun PagingComposeScreen(pagingViewModel: PagingViewModel) {

    val localItem = pagingViewModel.items.collectAsLazyPagingItems()
    val remoteItem = pagingViewModel.remotePagingFlow.collectAsLazyPagingItems()

    // item이 LazyColum 에 들어왔을 때 아이템이 있는 곳까지 scroll을 내려주는 역할
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(localItem) {
        coroutineScope.launch {
            val lastIndex = localItem.itemCount - 1
            if (lastIndex >= 0) {
                lazyListState.scrollToItem(index = localItem.itemCount - 1)
            }
        }
    }

    localItem.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                progressIndicator()
            }

            loadState.append is LoadState.Loading -> {
                progressIndicator()
            }

            loadState.append is LoadState.Error -> {
                Text("Error loading more items")
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                onClick = { pagingViewModel.insertUser("TestTest") }
            ) {
                Text(text = "Add Button")
            }

            Button(
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                onClick = { pagingViewModel.deleteAllUser() }
            ) {
                Text(text = "Delete Button")
            }
        }


        LazyColumn(state = lazyListState) {
            items(localItem.itemCount) { idx ->
                val item = localItem[idx]
                if (item != null) {
                    ItemView(item)
                } else {
                    // 로딩 or placeHolder Ui
                }
            }
        }
    }
}

@Composable
fun ItemView(item: PagingItemEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = item.title ?: "")
    }
}
