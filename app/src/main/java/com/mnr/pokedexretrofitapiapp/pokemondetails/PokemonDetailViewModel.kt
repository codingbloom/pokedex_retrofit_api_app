package com.mnr.pokedexretrofitapiapp.pokemondetails

import androidx.lifecycle.ViewModel
import com.mnr.pokedexretrofitapiapp.data.remote.responses.Pokemon
import com.mnr.pokedexretrofitapiapp.repository.PokemonRepository
import com.mnr.pokedexretrofitapiapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(

    private val repository: PokemonRepository

): ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String) : Resource<Pokemon> {

        return repository.getPokemonName(pokemonName)
    }
}