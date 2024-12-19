package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.model

import kotlin.random.Random

//data class PokemonInfo(
//    val id: Int,
//    val name: String,
//    val height: Int,
//    val weight: Int,
//    val experience: Int,
//    val types: List<TypeResponse>,
//    val hp: Int = Random.nextInt(maxHp),
//    val attack: Int = Random.nextInt(maxAttack),
//    val defense: Int = Random.nextInt(maxDefense),
//    val speed: Int = Random.nextInt(maxSpeed),
//    val exp: Int = Random.nextInt(maxExp)
//) {
//
//    @JsonClass(generateAdapter = true)
//    data class TypeResponse(
//        @field:Json(name = "slot") val slot: Int,
//        @field:Json(name = "type") val type: Type
//    )
//
//    @JsonClass(generateAdapter = true)
//    data class Type(
//        @field:Json(name = "name") val name: String
//    )
//
//    companion object {
//        const val maxHp = 300
//        const val maxAttack = 300
//        const val maxDefense = 300
//        const val maxSpeed = 300
//        const val maxExp = 1000
//    }
//}
