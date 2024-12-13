package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.remote

data class PokemonListDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)