package com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.composeBasicUser

import kotlinx.coroutines.delay

class ComposeBasicUserRepositoryImpl : ComposeBasicUserRepository {
    private var users = mutableListOf<ComposeBasicUser>()

    override suspend fun getUsers(): List<ComposeBasicUser> {
        delay(1000)  // Simulating network delay
        return users
    }

    override suspend fun addUser(user: ComposeBasicUser): List<ComposeBasicUser> {
        delay(500)
        users.add(user)
        return users
    }

    override suspend fun deleteUser(user: ComposeBasicUser): List<ComposeBasicUser> {
        delay(500)
        users.remove(user)
        return users
    }

    override suspend fun clearUsers(): List<ComposeBasicUser> {
        delay(500)
        users.clear()
        return users
    }


}