package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity

@Dao
interface CoroutineTestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(coroutineTestEntity: CoroutineTestEntity): Long // return rowId (-1 is error)

    @Delete
    suspend fun deleteFavorite(coroutineTestEntity: CoroutineTestEntity): Int // return success row count (0 is error)
}