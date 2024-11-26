package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity

@Database(entities = [CoroutineTestEntity::class], version = 1)
abstract class CoroutineTestDatabase : RoomDatabase() {
    abstract fun getCoroutineTestDao(): CoroutineTestDao

    companion object {
        const val DATABASE_NAME = "coroutine_test.db"
    }
}