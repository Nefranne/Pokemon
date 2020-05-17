package com.example.td3.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.td3.Constant;
import com.example.td3.PokeApi;
import com.example.td3.R;
import com.example.td3.presentation.controller.MainController;
import com.example.td3.presentation.injection;
import com.example.td3.presentation.model.Pokemon;
import com.example.td3.presentation.model.RestPokemonResponse;
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


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       controller= new MainController(
               this,
                injection.getGSon(),
                injection.getsharedPreferenceInstance(getApplicationContext());
       );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // controller = new MainController();
        controller.onStart();
    }

    /*private List<Pokemon> getDataFromCache() {
        String jsonPokemon = sharedPreferences.getString("jsonPokemonList",null);
        if (jsonPokemon == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Pokemon>>() {}.getType();
            return gson.fromJson(jsonPokemon, listType);
        }
    }*/



    public void showList (List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
      //  List<String> input = new ArrayList<>();
        mAdapter = new ListAdapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }





    public void showSucces() {
        Toast.makeText(this, "API success", Toast.LENGTH_SHORT).show();
    }

    public void showError() {
        Toast.makeText(this, "API Error", Toast.LENGTH_SHORT).show();
    }



}

