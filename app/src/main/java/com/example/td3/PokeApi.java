package com.example.td3;

import com.example.td3.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {

    @GET("/api/v2/pokemon")
    Call<RestPokemonResponse> getPokemonResponse ();

 /*   @GET("/api/v2/Ability")
    Call<RestPokemonResponse> getPokemonResponse ();*/
}