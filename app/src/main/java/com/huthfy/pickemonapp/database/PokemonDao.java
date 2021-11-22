package com.huthfy.pickemonapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.huthfy.pickemonapp.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PokemonDao {

    //insert fav pokemon
    @Insert
    void insert(Pokemon pokemon);

    //delete fav pokemon
    @Query("DELETE  FROM Pokemon WHERE id=:pokemonId")
    void delete(int pokemonId);

    //get all fav pokemon
    @Query("SELECT * FROM Pokemon")
    LiveData<List<Pokemon>> getFavPokemon();
}
