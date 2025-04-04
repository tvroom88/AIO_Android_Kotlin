package com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.composeBasicUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ComposeBasicUserViewModel : ViewModel() {
    private var composeBasicUserRepository: ComposeBasicUserRepository =
        ComposeBasicUserRepositoryImpl()

    // UI state
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // Search query and filtered users
    private val _filteredUsers = MutableStateFlow<List<ComposeBasicUser>>(emptyList())
    val filteredUsers: StateFlow<List<ComposeBasicUser>> = _filteredUsers.asStateFlow()

    init {
        addAllUser(getTestData())
    }

    // Fetch users from the repository
    private fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading  // Set loading state
            try {
                val users = composeBasicUserRepository.getUsers()  // Fetch users
                _uiState.value = UiState.Success(
                    users,
                    users.isEmpty() // true - empty, false - not empty
                )
                _filteredUsers.value = users  // Emit the fetched users
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")  // Handle error
            }
        }
    }

    private fun addUser(user:ComposeBasicUser){
        viewModelScope.launch {
            _uiState.value = UiState.Loading  // Set loading state
            try {
                val users = composeBasicUserRepository.addUser(user)  // Fetch users
                _uiState.value = UiState.Success(
                    users,
                    users.isEmpty() // true - empty, false - not empty
                )
                _filteredUsers.value = users  // Emit the fetched users

                fetchUsers()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")  // Handle error
            }
        }

    }

    private fun addAllUser(userList:List<ComposeBasicUser>){
        for(user in userList){
            addUser(user)
        }
    }

    fun deleteUser(user: ComposeBasicUser) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading  // Set loading state
            try {
                val users = composeBasicUserRepository.deleteUser(user)  // Fetch users
                _uiState.value = UiState.Success(
                    users,
                    users.isEmpty() // true - empty, false - not empty
                )
                _filteredUsers.value = users  // Emit the fetched users
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")  // Handle error
            }
        }
    }

    fun getTestData(): List<ComposeBasicUser> {
        return listOf(
            ComposeBasicUser("A", 22),
            ComposeBasicUser("B", 22),
            ComposeBasicUser("C", 22),
            ComposeBasicUser("D", 22),
            ComposeBasicUser("E", 22),
        )
    }
}

sealed class UiState {
    data object Loading : UiState()  // Represents the loading state
    data class Success(
        val users: List<ComposeBasicUser>?,
        val isEmpty: Boolean
    ) : UiState()  // Represents a successful data fetch

    data class Error(val message: String) :
        UiState()  // Represents an error state with an error message
}