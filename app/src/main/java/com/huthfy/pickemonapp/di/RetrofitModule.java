package com.huthfy.pickemonapp.di;

import com.huthfy.pickemonapp.network.PokemonApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {
    public static final String BASE_URL = "https://pokeapi.co/api/v2/";

    @Provides
    @Singleton
    PokemonApiInterface providePokemonApiInterface(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(PokemonApiInterface.class);
    }
}
