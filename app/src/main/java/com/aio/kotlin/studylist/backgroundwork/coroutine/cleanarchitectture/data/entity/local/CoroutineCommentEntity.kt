package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coroutine_comment_table")
data class CoroutineCommentEntity(
    @PrimaryKey(autoGenerate = true) val myId: Int? = null,  // autoGenerate 설정
    @ColumnInfo(name = "postId")
    val postId: Int,
    @ColumnInfo(name = "id")
    val customId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "body")
    val body: String,
)
