package com.huthfy.pickemonapp.database;
/* steps
1- make this class in singleton pattern
2- declare Dao interface
3- #done#
 */

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.huthfy.pickemonapp.model.Pokemon;

@Database(entities = Pokemon.class, version = 4)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDao getDao();

}
