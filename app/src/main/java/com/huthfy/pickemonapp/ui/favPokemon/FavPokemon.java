package com.huthfy.pickemonapp.ui.favPokemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.huthfy.pickemonapp.R;
import com.huthfy.pickemonapp.model.Pokemon;
import com.huthfy.pickemonapp.ui.mainActivity.MainActivity;
import com.huthfy.pickemonapp.ui.mainActivity.PokemonAdapter;
import com.huthfy.pickemonapp.ui.mainActivity.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavPokemon extends AppCompatActivity {

    FavPokemonViewModel favPokemonViewModel;
    RecyclerView pokemonRecycler;
    PokemonAdapter pokemonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_pokemon);



        //favPokemon ViewModel
        favPokemonViewModel =new ViewModelProvider(this).get(FavPokemonViewModel.class);

        //recycler
        pokemonRecycler = findViewById(R.id.fav_pokemons_recycler);

        //adapter
        pokemonAdapter = new PokemonAdapter(this);
        pokemonRecycler.setAdapter(pokemonAdapter);

        favPokemonViewModel.getFavPokemon();
        favPokemonViewModel.getFavPokemonsLivedata().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                ArrayList<Pokemon> arrayList = new ArrayList<>();
                arrayList.addAll(pokemons);
                pokemonAdapter.setArrayList(arrayList);
            }
        });

        swipePokemon();
    }

    public void swipePokemon(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemonAdapter.getPokemonAt(position);
                favPokemonViewModel.delete(pokemon.getId());
                pokemonAdapter.notifyDataSetChanged();
                Toast.makeText(FavPokemon.this, "pokemon deleted from database", Toast.LENGTH_SHORT).show();
            }
        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(pokemonRecycler);
    }
}