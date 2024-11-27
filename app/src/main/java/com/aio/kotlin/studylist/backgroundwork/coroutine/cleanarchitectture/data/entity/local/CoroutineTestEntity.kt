package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coroutine_test_table")
data class CoroutineTestEntity(
    @PrimaryKey(autoGenerate = true) val myId: Int? = null,  // autoGenerate 설정
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "id")
    val customId: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "body")
    val body: String,
)
