package com.mnr.pokedexretrofitapiapp.repository

import com.mnr.pokedexretrofitapiapp.data.remote.PokeApi
import com.mnr.pokedexretrofitapiapp.data.remote.responses.Pokemon
import com.mnr.pokedexretrofitapiapp.data.remote.responses.PokemonList
import com.mnr.pokedexretrofitapiapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class PokemonRepository @Inject constructor(

    private val api: PokeApi

) {

    //for Pokemon List
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {

        val response = try {

            api.getPokemonList(limit, offset)

        } catch (e: Exception) {

            return Resource.Error("An unknown error occured.")
        }

        //if everything is fine then return this
        return Resource.Success(response)
    }

    //get Pokemon Name
    suspend fun getPokemonName(pokemonName: String): Resource<Pokemon> {

        val response = try {

            api.getPokemonInfo(pokemonName)

        } catch (e: Exception) {

            return Resource.Error("An unknown error occured.")
        }

        //if everything is fine then return this
        return Resource.Success(response)
    }
}