package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.di

import android.content.Context
import androidx.room.Room
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

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext appContext: Context): CoroutineTestDatabase {
        return Room.databaseBuilder(
            appContext,
            CoroutineTestDatabase::class.java,
            CoroutineTestDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesDao(database: CoroutineTestDatabase): CoroutineTestDao {
        return database.getCoroutineTestDao()
    }
}