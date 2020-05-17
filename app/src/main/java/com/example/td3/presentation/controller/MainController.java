package com.example.td3.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.td3.Constant;
import com.example.td3.PokeApi;
import com.example.td3.presentation.injection;
import com.example.td3.presentation.model.Pokemon;
import com.example.td3.presentation.model.RestPokemonResponse;
import com.example.td3.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;



    public MainController(MainActivity mainActivity,Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }
    public void onStart () {

        List<Pokemon> pokemonList = getDataFromCache();
        if ( pokemonList != null) {
            view.showList(pokemonList);
        }
        else {
            makeApiCall();
        }
    }
    private void makeApiCall () {
        Call<RestPokemonResponse> call = injection.getPokeApi().getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pokemon> pokemonList = response.body().getResults();
                    saveList(pokemonList);
                    view.showSucces();
                    view.showList(pokemonList);
                    //Toast.makeText(getApplicationContext(), "API success", Toast.LENGTH_SHORT).show();
                }
                else view.showError();
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                view.showError();

            }
        });
    }
    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                //   .putInt("cle_integer",3);
                .putString(Constant.KEY_POKEMON_LIST, jsonString)
                .apply();

        Toast.makeText(view.getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }
    private List<Pokemon> getDataFromCache() {
        String jsonPokemon = sharedPreferences.getString(Constant.KEY_POKEMON_LIST, null);
        if (jsonPokemon == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Pokemon>>() {}.getType();
            return gson.fromJson(jsonPokemon, listType);
        }
    }
    public void onItemClick(Pokemon pokemon) {
        view.navigateToTheDetails(pokemon);
    }
    public void onButtonClick() {

    }
}
