package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paging")
data class PagingItemEntity(
    @PrimaryKey val id: Int = 0,
    val userId: Int? = null,
    val title: String? = null
)