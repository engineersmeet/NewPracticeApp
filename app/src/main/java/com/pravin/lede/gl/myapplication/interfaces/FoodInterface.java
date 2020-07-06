package com.pravin.lede.gl.myapplication.interfaces;

import com.pravin.lede.gl.myapplication.models.FoodModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodInterface {

    @GET("get_food")
    Call<List<FoodModel>> get_food();

}
