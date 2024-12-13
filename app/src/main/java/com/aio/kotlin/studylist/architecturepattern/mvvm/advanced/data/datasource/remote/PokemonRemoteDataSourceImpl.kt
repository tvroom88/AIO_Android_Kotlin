package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.remote

import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.remote.Pokemon
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.network.PokemonListApi
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(private val pokemonListApi: PokemonListApi) :
    PokemonRemoteDataSource {

    override suspend fun fetchPokemonList(page: Int): Result<List<Pokemon>> = try {
        val response = pokemonListApi.fetchPokemonList(PAGING_SIZE, PAGING_SIZE * page)
        if(response.isSuccessful){
            response.body()?.let { // 응답이 성공적이면 Response.body()를 Result.Success로 변환하여 반환
                Result.success(it.results)
            } ?: Result.failure(NullPointerException("Body is null"))
        }else{ // 응답 코드가 성공이 아니면 Result.Failure로 변환하여 반환
            Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
        }
    } catch (e: Exception) { // 예외가 발생하면 Result.Failure로 처리
        Result.failure(e)
    }

    companion object {
        private const val PAGING_SIZE = 20
    }
}