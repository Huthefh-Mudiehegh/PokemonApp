package com.huthfy.pickemonapp.ui.favPokemon;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.pickemonapp.model.Pokemon;
import com.huthfy.pickemonapp.repo.PokemonRepo;

import java.util.List;

public class FavPokemonViewModel extends ViewModel {

    private LiveData<List<Pokemon>> favPokemonsLivedata;
    PokemonRepo pokemonRepo;

    @ViewModelInject
    public FavPokemonViewModel(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    //operations of room database

    //insert pokemon in room database
    public void insert(Pokemon pokemon){
        pokemonRepo.insert(pokemon);
    }

    //delete pokemon in room database
    public void delete(int pokemonId){
        pokemonRepo.delete(pokemonId);
    }

    //get all pokemons in room database
    public void getFavPokemon(){
        favPokemonsLivedata = pokemonRepo.getFavPokemon();
    }

    public LiveData<List<Pokemon>> getFavPokemonsLivedata() {
        return favPokemonsLivedata;
    }
}
