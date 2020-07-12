package com.pravin.lede.gl.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class FoodOrderModel {

    @SerializedName("foodId")
    int foodId;

    @SerializedName("foodCount")
    int foodCount;

    @SerializedName("custName")
    String custName;

    @SerializedName("foodCost")
    int foodCost;

    @SerializedName("foodModel")
    FoodModel foodModel;

    public FoodOrderModel(int foodId, int foodCount, String custName, int foodCost, FoodModel foodModel) {
        this.foodId = foodId;
        this.foodCount = foodCount;
        this.custName = custName;
        this.foodCost = foodCost;
        this.foodModel = foodModel;
    }

    public FoodModel getFoodModel() {
        return foodModel;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public String getCustName() {
        return custName;
    }

    public int getFoodCost() {
        return foodCost;
    }
}
