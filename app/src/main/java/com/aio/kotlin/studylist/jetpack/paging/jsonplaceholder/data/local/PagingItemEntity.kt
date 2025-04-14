package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paging")
data class PagingItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val albumId: Int? = null,
    val title: String? = null,
    val url: String? = null,
    val thumbnailUrl: String? = null,
)