package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineCommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoroutineCommentDao {

    @Query("SELECT * FROM coroutine_comment_table")
    fun getAllData() : Flow<List<CoroutineCommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(coroutineTestEntities: List<CoroutineCommentEntity>)

    @Query("DELETE FROM coroutine_comment_table")
    suspend fun deleteAll()
}