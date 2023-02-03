package com.mnr.pokedexretrofitapiapp.di

import com.google.gson.Gson
import com.mnr.pokedexretrofitapiapp.data.remote.PokeApi
import com.mnr.pokedexretrofitapiapp.repository.PokemonRepository
import com.mnr.pokedexretrofitapiapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun proviedPokemonRepository(api: PokeApi) = PokemonRepository(api)


    @Singleton
    @Provides
    fun providePokeApi(): PokeApi {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeApi::class.java)

    }
}