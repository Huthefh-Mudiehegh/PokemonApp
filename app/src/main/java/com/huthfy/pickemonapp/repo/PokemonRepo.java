package com.huthfy.pickemonapp.repo;

import com.huthfy.pickemonapp.model.PokemonResponse;
import com.huthfy.pickemonapp.network.PokemonApiInterface;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class PokemonRepo {

    PokemonApiInterface pokemonApiInterface;

    @Inject
    public PokemonRepo(PokemonApiInterface pokemonApiInterface) {
        this.pokemonApiInterface = pokemonApiInterface;
    }

    public Observable<PokemonResponse> getPokemons(){
        return pokemonApiInterface.getPokemons();
    }
}
