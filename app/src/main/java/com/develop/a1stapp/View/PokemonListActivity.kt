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
        fetchData()
        getPokemonList()
    }

    private fun getPokemonList() {
        pokemonListVM.getPokemonList()
    }

    private fun fetchData() {
        val pokemonService = ServiceBuilder.build(PokemonService::class.java)
        val requestCall = pokemonService.getPokemonList()

        requestCall.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                println("status code ${response.code()}")
                if (response.isSuccessful) { //status code in range of 200 ~ 299
                    val respondedPokemon = response.body()!!
                    val pokemonResults = respondedPokemon.results
                    updateUI(pokemonResults!!)
                } else { //status code in range of 300s, 400s, 500s
                    Toast.makeText(this@PokemonListActivity, "failed to get item", Toast.LENGTH_LONG).show()
                }
            }

            //handle error
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Toast.makeText(this@PokemonListActivity, "fetch failed" + t.toString(), Toast.LENGTH_LONG).show()
            }
        })
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

