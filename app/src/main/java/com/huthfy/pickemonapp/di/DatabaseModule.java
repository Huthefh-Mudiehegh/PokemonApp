package com.huthfy.pickemonapp.di;

import android.app.Application;

import androidx.room.Room;

import com.huthfy.pickemonapp.database.PokemonDao;
import com.huthfy.pickemonapp.database.PokemonDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static  PokemonDatabase provideDatabase(Application application){
        return Room.databaseBuilder(application,PokemonDatabase.class,"PokemonDatabase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }


    @Provides
    @Singleton
    PokemonDao  ProvidePokemonDao(PokemonDatabase pokemonDatabase){
        return pokemonDatabase.getDao();
    }
}
