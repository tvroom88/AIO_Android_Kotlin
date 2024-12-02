package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database.CoroutineCommentDao
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database.CoroutineTestDao
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database.CoroutineTestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineTestDbModule {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // 스키마 변경을 위한 SQL 명령어 추가
            database.execSQL("ALTER TABLE coroutine_test_table ADD COLUMN new_column_name TEXT")
        }
    }

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext appContext: Context): CoroutineTestDatabase {
        return Room.databaseBuilder(
            appContext,
            CoroutineTestDatabase::class.java,
            CoroutineTestDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration() // 마이그레이션 추가
            .build()
    }

    @Singleton
    @Provides
    fun providesDao(database: CoroutineTestDatabase): CoroutineTestDao {
        return database.getCoroutineTestDao()
    }

    @Singleton
    @Provides
    fun providesCommentDao(database: CoroutineTestDatabase): CoroutineCommentDao {
        return database.getCoroutineCommentDao()
    }
}