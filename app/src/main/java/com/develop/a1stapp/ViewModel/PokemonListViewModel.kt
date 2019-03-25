package com.develop.a1stapp.ViewModel

import android.arch.lifecycle.ViewModel
import com.develop.a1stapp.Model.Pokemon
import com.develop.a1stapp.Model.PokemonResult
import com.develop.a1stapp.Service.PokemonService
import com.develop.a1stapp.Service.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListViewModel: ViewModel() {
    //this flag should be observer to add or remove loading animation
    var isLoading: Boolean = true
    val pokemonService = ServiceBuilder.build(PokemonService::class.java)
    var pokemonList: List<PokemonResult>? = null

    fun getPokemonList(callback:(Boolean) -> Unit) {
        val requestCall = pokemonService.getPokemonList()
        handleCallback(requestCall) { pokemon: Pokemon?, success ->
            isLoading = false
            if (success) {
                println("fetch done $pokemon")
                pokemonList = pokemon!!.results
                callback(true)
            } else {
                println("fetch failed")
                callback(false)
            }
        }
    }

    //TO DO: handle call back or request should be in another Class (service)
    private fun<T> handleCallback(request: Call<T>, callback:(T?, Boolean)->Unit) {
        request.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                println("status code ${response.code()}")
                if (response.isSuccessful) {
                    callback(response.body(), true)
                } else {
                    println("failed to get item")
                    callback(null, false)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                println("fetch failed ${t.toString()}")
                callback(null, false)
            }
        })
    }
}