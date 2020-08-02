package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pravin.lede.gl.myapplication.MyApplication;
import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.adapter.FoodAdapter;
import com.pravin.lede.gl.myapplication.enm.FragmentType;
import com.pravin.lede.gl.myapplication.fragments.MapFragment;
import com.pravin.lede.gl.myapplication.fragments.MyCartFragment;
import com.pravin.lede.gl.myapplication.fragments.SelectOrdersFragment;
import com.pravin.lede.gl.myapplication.interfaces.CalculatePrice;
import com.pravin.lede.gl.myapplication.interfaces.FoodInterface;
import com.pravin.lede.gl.myapplication.interfaces.FoodOrderListener;
import com.pravin.lede.gl.myapplication.models.FoodModel;
import com.pravin.lede.gl.myapplication.models.FoodOrderModel;
import com.pravin.lede.gl.myapplication.utils.MyDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodActivity extends AppCompatActivity implements CalculatePrice {
    FrameLayout frameLayout;

    TextView placeOrderTextView;
    TextView placeOrderInfoTextView;
    TextView homeTextView;
    TextView mapTextView;

    SelectOrdersFragment selectOrdersFragment;
    MyCartFragment myCartFragment;
    MapFragment mapFragment;
    private TextView drawLocation;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        MyDatabase myDatabase=new MyDatabase(this,2);
        myDatabase.DeleteLocations();
        init();
        settingFragment();
        setOnClickListener();


    }

    private void settingFragment() {

        selectOrdersFragment = new SelectOrdersFragment();
        myCartFragment = new MyCartFragment();
        mapFragment = new MapFragment();

        //Add both the frags
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, selectOrdersFragment)
                .add(R.id.fragment_layout, myCartFragment).add(R.id.fragment_layout, mapFragment).commit();
        displayFragment(FragmentType.HOME);

        MyApplication.getSingletonObject().setCalculatePriceListener(this);
    }

    private void init() {

        frameLayout = findViewById(R.id.fragment_layout);
        placeOrderTextView = findViewById(R.id.my_cart);
        placeOrderInfoTextView = findViewById(R.id.place_order_info);
        homeTextView = findViewById(R.id.home);
        mapTextView = findViewById(R.id.map);
        drawLocation = findViewById(R.id.draw_location);

    }

    private void setOnClickListener() {

        placeOrderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFragment(FragmentType.CART);
                myCartFragment.updateCartAdapter();
            }
        });

        homeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFragment(FragmentType.HOME);
            }
        });

        mapTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFragment(FragmentType.MAP);
            }
        });

        drawLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragment.drawLocations();
            }
        });

    }

    private void displayFragment(FragmentType type) {

        switch (type){
            case HOME:
                getSupportFragmentManager().beginTransaction().show(selectOrdersFragment).commit();
                getSupportFragmentManager().beginTransaction().hide(myCartFragment).commit();
                getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();
                break;
            case CART:
                getSupportFragmentManager().beginTransaction().show(myCartFragment).commit();
                getSupportFragmentManager().beginTransaction().hide(selectOrdersFragment).commit();
                getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();
                break;
            case MAP:
                getSupportFragmentManager().beginTransaction().show(mapFragment).commit();
                getSupportFragmentManager().beginTransaction().hide(selectOrdersFragment).commit();
                getSupportFragmentManager().beginTransaction().hide(myCartFragment).commit();
                break;
        }

    }


    private void placeMyOrder() {

        FoodInterface foodInterface = MyApplication.getSingletonObject().getRetrofitObject().create(FoodInterface.class);

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
    public void onCostChanged(String latestCost) {
        placeOrderInfoTextView.setText(latestCost);
    }
}
