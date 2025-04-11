package com.aio.kotlin.studylist.jetpack.paging.github.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.rememberAsyncImagePainter
import com.aio.kotlin.studylist.jetpack.paging.github.data.GitHubUser

@Composable
fun GitHubUserList(viewModel: GitHubViewModel) {
    val users: LazyPagingItems<GitHubUser> = viewModel.users.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

//        users.apply {
//            when {
//                loadState.refresh is LoadState.Loading -> {
//                    item { progressIndicator() }
//                }
//
//                loadState.append is LoadState.Loading -> {
//                    item { progressIndicator() }
//                }
//
//                loadState.append is LoadState.Error -> {
//                    item { Text("Error loading more items") }
//                }
//            }
//        }


        items(users.itemCount) { index ->
            val user = users[index]
            Log.d("testtest", "user : $user")
            if (user != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(user.avatarUrl),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(user.login)
                }
            }
        }


    }
}

@Composable
fun progressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}