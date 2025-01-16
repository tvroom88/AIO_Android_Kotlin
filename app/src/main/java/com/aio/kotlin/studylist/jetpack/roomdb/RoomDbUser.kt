package com.aio.kotlin.studylist.jetpack.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomDbUser(
    var name: String,
    var age: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

