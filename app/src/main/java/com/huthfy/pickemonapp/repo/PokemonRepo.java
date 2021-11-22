package com.huthfy.pickemonapp.repo;

import androidx.lifecycle.LiveData;

import com.huthfy.pickemonapp.database.PokemonDao;
import com.huthfy.pickemonapp.model.Pokemon;
import com.huthfy.pickemonapp.model.PokemonResponse;
import com.huthfy.pickemonapp.network.PokemonApiInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class PokemonRepo {

    PokemonApiInterface pokemonApiInterface;
    PokemonDao pokemonDao;

    @Inject
    public PokemonRepo(PokemonApiInterface pokemonApiInterface, PokemonDao pokemonDao) {
        this.pokemonApiInterface = pokemonApiInterface;
        this.pokemonDao = pokemonDao;
    }

    //get pokemons from server
    public Observable<PokemonResponse> getPokemons(){
        return pokemonApiInterface.getPokemons();
    }

    //insert pokemon in room database
    public void insert(Pokemon pokemon){
        pokemonDao.insert(pokemon);
    }

    //delete pokemon in room database
    public void delete(int pokemonId){
        pokemonDao.delete(pokemonId);
    }

    //get all pokemons in room database
    public LiveData<List<Pokemon>> getFavPokemon(){
        return pokemonDao.getFavPokemon();
    }







}
