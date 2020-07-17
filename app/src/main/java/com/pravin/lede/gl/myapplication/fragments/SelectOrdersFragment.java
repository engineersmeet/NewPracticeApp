package com.pravin.lede.gl.myapplication.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pravin.lede.gl.myapplication.MyApplication;
import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.activities.FoodActivity;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectOrdersFragment extends Fragment implements FoodOrderListener {

    RecyclerView recyclerView;

    private static SelectOrdersFragment selectOrdersFragment = null;

    public static SelectOrdersFragment getSelectOrdersFragment() {
        if (selectOrdersFragment == null) {
            selectOrdersFragment = new SelectOrdersFragment();
        }
        return selectOrdersFragment;
    }

    public SelectOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment Activity", "Fragment Home Resumed");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment Activity", "Fragment Home Paused");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment Activity", "Fragment Destroyed");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("Fragment Activity", "Fragment Home Creating");

        View view = inflater.inflate(R.layout.fragment_select_orders, container, false);
        recyclerView = view.findViewById(R.id.food_recycler);
        setAdapter();
        return view;
    }

    public void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        callSelectOrderService();
    }

    public void callSelectOrderService() {

        FoodInterface foodInterface = MyApplication.getSingletonObject().getRetrofitObject().create(FoodInterface.class);

        Call<List<FoodModel>> call = foodInterface.get_food();

        call.enqueue(new Callback<List<FoodModel>>() {
            @Override
            public void onResponse(Call<List<FoodModel>> call, Response<List<FoodModel>> response) {
                ArrayList<FoodModel> foodModelArrayList = (ArrayList<FoodModel>) response.body();
                recyclerView.setAdapter(new FoodAdapter(foodModelArrayList, SelectOrdersFragment.this));
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<FoodModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onOrderPlaced(FoodOrderModel foodOrderModel) {
        MyApplication.getSingletonObject().addOrRemoveItems(foodOrderModel);
        MyApplication.getSingletonObject().calculateLatestCost();
    }
}
