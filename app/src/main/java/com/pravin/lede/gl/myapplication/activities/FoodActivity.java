package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.adapter.FoodAdapter;
import com.pravin.lede.gl.myapplication.interfaces.FoodInterface;
import com.pravin.lede.gl.myapplication.models.FoodModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        recyclerView = findViewById(R.id.food_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://prointellects.com/welcome/").addConverterFactory(GsonConverterFactory.create()).build();
        FoodInterface foodInterface = retrofit.create(FoodInterface.class);
        Call<List<FoodModel>> call = foodInterface.get_food();
        call.enqueue(new Callback<List<FoodModel>>() {
            @Override
            public void onResponse(Call<List<FoodModel>> call, Response<List<FoodModel>> response) {

                ArrayList<FoodModel> foodModelArrayList = (ArrayList<FoodModel>) response.body();
                recyclerView.setAdapter(new FoodAdapter(foodModelArrayList));
                Toast.makeText(FoodActivity.this, "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<FoodModel>> call, Throwable t) {
                Toast.makeText(FoodActivity.this, "Failure", Toast.LENGTH_LONG).show();

            }
        });

    }
}
