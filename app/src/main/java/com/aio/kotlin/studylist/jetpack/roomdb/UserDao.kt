package com.aio.kotlin.studylist.jetpack.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user:RoomDbUser)

    @Update
    fun update(user:RoomDbUser)

    @Update
    fun delete(user:RoomDbUser)

    @Query("DELETE FROM RoomDbUser WHERE name = :name") // 'name'에 해당하는 유저를 삭제해라
    fun deleteUserByName(name: String)

    @Query("DELETE FROM RoomDbUser WHERE id = :id") // 'name'에 해당하는 유저를 삭제해라
    fun deleteUserById(id: Int)

    @Query("SELECT * FROM RoomDbUser") // 테이블의 모든 값을 가져와라
    fun getAll(): Flow<List<RoomDbUser>>
}