package com.develop.a1stapp.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.develop.a1stapp.Model.Pokemon
import com.develop.a1stapp.Model.PokemonDetail
import com.develop.a1stapp.Model.PokemonResult
import com.develop.a1stapp.R
import com.develop.a1stapp.Service.PokemonService
import com.develop.a1stapp.Service.ServiceBuilder
import com.develop.a1stapp.ViewModel.PokemonListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListActivity : AppCompatActivity() {

    private lateinit var pokemonListVM: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        pokemonListVM = PokemonListViewModel()
        getPokemonList()
    }

    private fun getPokemonList() {
        pokemonListVM.getPokemonList { success ->
            if (success) {
                updateUI(pokemonListVM.pokemonList!!)
            }
        }
    }

    private fun getPokemonDetail(id: Int) {
        val pokemonService = ServiceBuilder.build(PokemonService::class.java)
        var callPokemonDetail = pokemonService.getPokemonDetail(id)

        callPokemonDetail.enqueue(object : Callback<PokemonDetail>{
            override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {

            }

            override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                
            }
        })
    }

    private fun updateUI(pokemonResults: List<PokemonResult>) {
        runOnUiThread {
            recyclerView.adapter = PokemonListAdapter(pokemonResults, onImgClick)
        }
    }

    /**click to image in Cell to get Pokemon details*/
    private val onImgClick: (String) -> Unit = {value: String ->
        println(value)
        getPokemonDetails()
    }

    private fun getPokemonDetails() {

    }
}

