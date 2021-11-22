package com.huthfy.pickemonapp.ui.mainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.huthfy.pickemonapp.R;
import com.huthfy.pickemonapp.database.PokemonDao;
import com.huthfy.pickemonapp.model.Pokemon;
import com.huthfy.pickemonapp.ui.favPokemon.FavPokemon;
import com.huthfy.pickemonapp.ui.favPokemon.FavPokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    PokemonViewModel pokemonViewModel;
    FavPokemonViewModel favPokemonViewModel;
    RecyclerView pokemonRecycler;
    PokemonAdapter pokemonAdapter;

    Button favBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fav btn
        favBtn = findViewById(R.id.main_fav_btn);
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FavPokemon.class));
            }
        });

        //Pokemon ViewModel
        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        //favPokemon ViewModel
        favPokemonViewModel = new ViewModelProvider(this).get(FavPokemonViewModel.class);

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

        swipePokemon();


    }

    public void swipePokemon() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemonAdapter.getPokemonAt(position);

                favPokemonViewModel.insert(pokemon);
                pokemonAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "pokemon added to database", Toast.LENGTH_SHORT).show();


            }
        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(pokemonRecycler);
    }
}