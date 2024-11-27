package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity

@Dao
interface CoroutineTestDao {
    @Query("DELETE FROM coroutine_test_table WHERE userId = :userId AND id = :customId")
    suspend fun deleteByUserIdAndCustomId(userId: String, customId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(coroutineTestEntity: CoroutineTestEntity): Long // return rowId (-1 is error)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(coroutineTestEntities: List<CoroutineTestEntity>)

    @Delete
    suspend fun deleteData(coroutineTestEntity: CoroutineTestEntity): Int // return success row count (0 is error)

    @Query("SELECT * FROM coroutine_test_table")
    fun getAllData() : List<CoroutineTestEntity>

    @Query("DELETE FROM coroutine_test_table")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM coroutine_test_table")
    suspend fun getCount(): Int
}