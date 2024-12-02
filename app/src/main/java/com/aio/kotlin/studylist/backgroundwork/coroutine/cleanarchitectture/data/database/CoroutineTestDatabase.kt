package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineCommentEntity
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity

@Database(entities = [CoroutineTestEntity::class, CoroutineCommentEntity::class], version = 2)
abstract class CoroutineTestDatabase : RoomDatabase() {
    abstract fun getCoroutineTestDao(): CoroutineTestDao
    abstract fun getCoroutineCommentDao(): CoroutineCommentDao

    companion object {
        const val DATABASE_NAME = "coroutine_test.db"
    }
}