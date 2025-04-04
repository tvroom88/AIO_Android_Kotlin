package com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.composeBasicUser

interface ComposeBasicUserRepository {
    suspend fun getUsers(): List<ComposeBasicUser>
    suspend fun addUser(user: ComposeBasicUser): List<ComposeBasicUser>
    suspend fun deleteUser(user: ComposeBasicUser): List<ComposeBasicUser>
    suspend fun clearUsers(): List<ComposeBasicUser>
}