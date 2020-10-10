package com.pravin.lede.gl.myapplication.models;

import com.pravin.lede.gl.myapplication.MyApplication;
import com.pravin.lede.gl.myapplication.interfaces.FoodInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectOrderModel implements ISelectOrderModel{
    @Override
    public ArrayList<FoodModel> getSelectOrderListFromService() {
        return callSelectOrderService();
    }

    public ArrayList<FoodModel> callSelectOrderService() {

        ArrayList<FoodModel> foodModelArrayList = new ArrayList<FoodModel>();
        FoodInterface foodInterface = MyApplication.getSingletonObject().getRetrofitObject().create(FoodInterface.class);

        Call<List<FoodModel>> call = foodInterface.get_food();
        call.enqueue(new Callback<List<FoodModel>>() {

            @Override
            public void onResponse(Call<List<FoodModel>> call, Response<List<FoodModel>> response) {
                foodModelArrayList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<FoodModel>> call, Throwable t) {
                foodModelArrayList.addAll(null);
            }
        });

        return foodModelArrayList;
    }
}
