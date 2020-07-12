package com.pravin.lede.gl.myapplication.interfaces;

import com.pravin.lede.gl.myapplication.models.FoodModel;
import com.pravin.lede.gl.myapplication.models.FoodOrderModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodInterface {

    @GET("get_food")
    Call<List<FoodModel>> get_food();

    @POST("place_order/")
    Call<String> placeOrder(@Body ArrayList<FoodOrderModel> orderModel);

}
