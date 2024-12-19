package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.remote

import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.model.Pokemon

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)