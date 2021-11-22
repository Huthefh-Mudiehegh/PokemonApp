package com.huthfy.pickemonapp.ui.mainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huthfy.pickemonapp.R;
import com.huthfy.pickemonapp.model.Pokemon;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    PokemonViewModel pokemonViewModel;
    RecyclerView pokemonRecycler;
    PokemonAdapter pokemonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pokemon ViewModel
        pokemonViewModel =new ViewModelProvider(this).get(PokemonViewModel.class);

        //recycler
        pokemonRecycler = findViewById(R.id.main_pokemons_recycler);

        //adapter
        pokemonAdapter = new PokemonAdapter(this);
        pokemonRecycler.setAdapter(pokemonAdapter);

        //get Pokemons
        pokemonViewModel.getPokemonsFromApi();
        pokemonViewModel.getPokemonsLivedata().observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                pokemonAdapter.setArrayList(pokemons);

            }
        });
    }
}