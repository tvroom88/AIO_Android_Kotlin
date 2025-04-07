package com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.composeBasicUser

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeBasicUserScreen(viewModel: ComposeBasicUserViewModel) {

    val uiState by viewModel.uiState.collectAsState()  // Collect the UI state
    val filteredUsers by viewModel.filteredUsers.collectAsState()  // Collect filtered users
//    val users = (uiState as? UiState.Success)?.users ?: emptyList()
    Log.d("ComposeBasicUserScreen", "filteredUsers : $uiState")

    when (val state = uiState) {
        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Success -> {
            Log.d("ComposeBasicUserScreen", "in Success")

            if (state.isEmpty) {
                Text(text = "User list is Empty")
            } else {
                LazyColumn(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp), // 아이템 사이의 간격
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(filteredUsers) { user ->
                        UserItem(
                            user,
                            onDeleteUser = {
                                viewModel.deleteUser(user)
                                Log.d("ComposeBasicUserScreen", "onDeleteButtonClicked")
                            }
                        )  // Display user items
                    }
                }
            }
        }

        is UiState.Error -> {
            Text(text = (uiState as UiState.Error).message)  // Show error message
        }
    }
}

@Composable
fun UserItem(user: ComposeBasicUser, onDeleteUser: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = user.name)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp)) // 버튼 모서리 둥글게
                .background(Brush.horizontalGradient(listOf(Color.Red, Color.Magenta)))
                .padding(8.dp)
                .clickable { onDeleteUser() }
        ) {
            Text(
                text = "삭제",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator( // Show a loading spinner
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
        )
    }

}

@Preview
@Composable
fun ComposeBasicPreviewA() {
}

@Preview
@Composable
fun ComposeBasicPreviewB() {
    LoadingScreen()
}
