package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * PagingItemEntity - Object Class
 * PagingItemDao - interface
 */

@Database(entities = [PagingItemEntity::class], version = 2)
abstract class PagingRoomDatabase : RoomDatabase() {
    abstract fun pagingItemDao(): PagingItemDao

    companion object {
        private var instance: PagingRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PagingRoomDatabase? {
            if (instance == null) {
                synchronized(PagingRoomDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PagingRoomDatabase::class.java,
                        "paging-database"
                    ).build()
                }
            }
            return instance
        }
    }
}
