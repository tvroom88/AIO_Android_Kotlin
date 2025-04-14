package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.remote

import com.google.gson.annotations.SerializedName

data class PagingAlbumItem(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("userId")
    val albumId: Int? = null,
    @SerializedName("title")
    val title: String? = null
)