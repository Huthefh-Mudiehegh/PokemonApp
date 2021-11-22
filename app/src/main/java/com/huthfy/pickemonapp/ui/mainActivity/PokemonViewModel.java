package com.huthfy.pickemonapp.ui.mainActivity;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.pickemonapp.model.Pokemon;
import com.huthfy.pickemonapp.model.PokemonResponse;
import com.huthfy.pickemonapp.repo.PokemonRepo;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private static final String TAG = "get_pokemons_state";
    private MutableLiveData<ArrayList<Pokemon>> pokemonsLivedata = new MutableLiveData<>();
    PokemonRepo pokemonRepo;

    @ViewModelInject
    public PokemonViewModel(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    public void getPokemonsFromApi(){
        pokemonRepo.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Throwable {
                        ArrayList<Pokemon> arrayList = pokemonResponse.getResults();
                        for (Pokemon pokemon :arrayList){
                            String url = pokemon.getUrl();
                            String [] urllist = url.split("/");

                            pokemon.setUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+urllist[urllist.length-1]+".png");
                        }
                        return arrayList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {
                    Log.d(TAG, "getPokemons: success") ;
                    pokemonsLivedata.setValue(result);
                        },
                        f-> Log.d(TAG, "getPokemons: failed"));

    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonsLivedata() {
        return pokemonsLivedata;
    }
}
