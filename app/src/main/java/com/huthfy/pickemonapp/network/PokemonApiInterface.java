package com.huthfy.pickemonapp.network;

import com.huthfy.pickemonapp.model.Pokemon;
import com.huthfy.pickemonapp.model.PokemonResponse;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApiInterface {
    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
