package com.example.td3.presentation.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
    private ImageView imageview;
    private TextView size;
    private TextView type;
    private TextView names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_acctivity);
        txtDetail = findViewById(R.id.detail_txt);
        size = findViewById(R.id.textView);
        type = findViewById(R.id.textView2);
        names = findViewById(R.id.textView3);
        imageview = findViewById(R.id.icon);
        Intent intent = getIntent();
        String PokemonJson = intent.getStringExtra(Constant.KEY_POKEMON );
        Pokemon pokemon = injection.getGSon().fromJson(PokemonJson, Pokemon.class);
        showDetail(pokemon,imageview);

    }

    private void showDetail(Pokemon pokemon,ImageView view) {
        txtDetail.setText(pokemon.getName());
       // size.setText(pokemo);

        Glide.with(view)
                .load(Constant.URL_IMAGE + pokemon.getNumber() + ".png")
                .into(view);
    }

}

