package com.aio.kotlin.studylist.jetpack.paging.github.data

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
