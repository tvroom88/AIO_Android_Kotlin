package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.local.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val DATABASE_NAME = "coroutine_test.db"
    }
}
