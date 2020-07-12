package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pravin.lede.gl.myapplication.MyApplication;
import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.adapter.FoodAdapter;
import com.pravin.lede.gl.myapplication.interfaces.FoodInterface;
import com.pravin.lede.gl.myapplication.interfaces.FoodOrderListener;
import com.pravin.lede.gl.myapplication.models.FoodModel;
import com.pravin.lede.gl.myapplication.models.FoodOrderModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodActivity extends AppCompatActivity implements FoodOrderListener {
    RecyclerView recyclerView;

    Retrofit retrofit;
    FoodInterface foodInterface;

    TextView placeOrderTextView;
    TextView placeOrderInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        recyclerView = findViewById(R.id.food_recycler);
        placeOrderTextView = findViewById(R.id.place_order_button);
        placeOrderInfoTextView = findViewById(R.id.place_order_info);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        retrofit = new Retrofit.Builder().baseUrl("https://prointellects.com/welcome/").addConverterFactory(GsonConverterFactory.create()).build();
        foodInterface = retrofit.create(FoodInterface.class);

        Call<List<FoodModel>> call = foodInterface.get_food();
        call.enqueue(new Callback<List<FoodModel>>() {
            @Override
            public void onResponse(Call<List<FoodModel>> call, Response<List<FoodModel>> response) {
                ArrayList<FoodModel> foodModelArrayList = (ArrayList<FoodModel>) response.body();
                recyclerView.setAdapter(new FoodAdapter(foodModelArrayList, FoodActivity.this));
                Toast.makeText(FoodActivity.this, "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<FoodModel>> call, Throwable t) {
                Toast.makeText(FoodActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });

        placeOrderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(FoodActivity.this,MainActivity.class);
            startActivity(intent);
               // placeMyOrder();
            }
        });

    }

    private void placeMyOrder() {

        Call<String> stringCall = foodInterface.placeOrder(MyApplication.getSingletonObject().getSelectedFoodOrderList());

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(FoodActivity.this, response.body(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(FoodActivity.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onOrderPlaced(FoodOrderModel foodOrderModel) {
        int totalCost = 0;
        MyApplication.getSingletonObject().addOrRemoveItems(foodOrderModel);
        for (FoodOrderModel orderModel : MyApplication.getSingletonObject().getSelectedFoodOrderList()) {
            totalCost += orderModel.getFoodCost() * orderModel.getFoodCount();
        }
        placeOrderInfoTextView.setText(String.format("Rs. %s", String.valueOf(totalCost)));


        // placeMyOrder(foodOrderModel);


    }


}
