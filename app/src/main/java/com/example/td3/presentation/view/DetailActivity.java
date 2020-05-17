package com.example.td3.presentation.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td3.Constant;
import com.example.td3.R;
import com.example.td3.presentation.controller.MainController;
import com.example.td3.presentation.injection;
import com.example.td3.presentation.model.Pokemon;
import com.google.gson.Gson;

import java.util.List;


public class DetailActivity extends AppCompatActivity {
    private MainController controller;
    private TextView txtDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_acctivity);
        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String PokemonJson = intent.getStringExtra(Constant.KEY_POKEMON );
        Pokemon pokemon = injection.getGSon().fromJson(PokemonJson, Pokemon.class);
        showDetail(pokemon);
    }

    private void showDetail(Pokemon pokemon) {
        txtDetail.setText(pokemon.getName());

    }

}

