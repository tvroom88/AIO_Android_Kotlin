package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coroutine_test_table")
data class CoroutineTestEntity(
    @PrimaryKey val myId: Int,
    @ColumnInfo(name = "userId")
    val userId: String,
    @ColumnInfo(name = "id")
    val customId: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "body")
    val body: String,
)
