package com.example.td3.presentation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.td3.Constant;
import com.example.td3.PokeApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class injection { // j'ai eu la flemme de le renommer en Singletons :)

    private static Gson gsonInstance;
    private static PokeApi pokeApiInstance;
    private static SharedPreferences sharedPreferenceInstance;

    public static Gson getGSon() {
        if (gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static PokeApi getPokeApi() {
        if (pokeApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGSon()))
                    .build();
            pokeApiInstance = retrofit.create(PokeApi.class);
        }
        return pokeApiInstance;
    }


    public static SharedPreferences getsharedPreferenceInstance(Context context) {
        if (sharedPreferenceInstance == null) {
            sharedPreferenceInstance = context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        }
        return sharedPreferenceInstance;
    }
}
