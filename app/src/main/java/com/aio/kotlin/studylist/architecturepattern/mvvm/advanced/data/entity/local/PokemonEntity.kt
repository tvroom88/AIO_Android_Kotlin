package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon") // 테이블 이름을 pokemon으로 설정
data class PokemonEntity(
    @PrimaryKey val name: String,
    val url: String,
    var page: Int,
)
