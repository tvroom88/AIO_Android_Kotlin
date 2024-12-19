package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.database.PokemonDao
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.database.PokemonDatabase
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database.CoroutineCommentDao
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database.CoroutineTestDao
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database.CoroutineTestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonDatabaseModulo {

    @Singleton
    @Provides
    fun providePokemonDataBase(@ApplicationContext appContext: Context): PokemonDatabase {
        return Room.databaseBuilder(
            appContext,
            PokemonDatabase::class.java,
            PokemonDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesPokemonDao(database: PokemonDatabase): PokemonDao {
        return database.pokemonDao()
    }
}