package com.develop.a1stapp.View

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.develop.a1stapp.Model.PokemonResult
import com.develop.a1stapp.R
import kotlinx.android.synthetic.main.item_row.view.*

class PokemonListAdapter(private val pokemonResults: List<PokemonResult>, private val imgClick: (String) -> Unit) : RecyclerView.Adapter<PokemonListViewHolder>(){

    override fun getItemCount(): Int {
        return pokemonResults.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PokemonListViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cell = layoutInflater.inflate(R.layout.item_row, p0, false)

        return PokemonListViewHolder(cell)
    }

    override fun onBindViewHolder(itemRow: PokemonListViewHolder, index: Int) {
        var pokemonResult = pokemonResults.get(index)
        val pokemonName = pokemonResult.name.toString()
        val pokemonDetailURL = pokemonResult.url.toString()
        itemRow.view.textView.text = pokemonName

        itemRow.view.img.setOnClickListener {
            imgClick(pokemonDetailURL)
        }

        itemRow.view.setOnClickListener {

        }
    }
}


class PokemonListViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}
