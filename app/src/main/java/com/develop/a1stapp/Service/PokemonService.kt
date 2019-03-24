package com.develop.a1stapp.Service

import com.develop.a1stapp.Model.Pokemon
import com.develop.a1stapp.Model.PokemonDetail
import com.develop.a1stapp.Model.PokemonResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    fun getPokemonList(): Call<Pokemon>

    @GET("pokemon/{id}")
    fun getPokemonDetail(@Path("id") id: Int): Call<PokemonDetail>
}


