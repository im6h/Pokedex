package com.vu.pokedex.Retrofit;

import com.vu.pokedex.Model.Pokedex;
import com.vu.pokedex.Model.Pokemon;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokeDex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();
}
