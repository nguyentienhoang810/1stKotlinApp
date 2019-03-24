package com.develop.a1stapp.Model

data class Pokemon(var count: Int = 0,
                   var next: String? = null,
                   var results: List<PokemonResult>? = null) {

}


data class PokemonResult(var name: String? = null,
                         var url: String? = null) {

}

data class PokemonDetail(var sprites: Sprites? = null) {

}

data class Sprites(var back_default: String? = null) {

}