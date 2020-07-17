package com.pravin.lede.gl.myapplication;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.pravin.lede.gl.myapplication.interfaces.CalculatePrice;
import com.pravin.lede.gl.myapplication.models.FoodOrderModel;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private static MyApplication myApplication = null;
    private ArrayList<FoodOrderModel> selectedFoodOrderList = new ArrayList<>();

    CalculatePrice calculatePrice;

    public Retrofit getRetrofitObject(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://prointellects.com/welcome/").addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

    public void setCalculatePriceListener(CalculatePrice calculatePrice) {
        this.calculatePrice = calculatePrice;
    }

    public MyApplication() {
    }

    public static MyApplication getSingletonObject() {
        if (myApplication == null) {
            myApplication = new MyApplication();
        }
        return myApplication;
    }

    public void addOrRemoveItems(FoodOrderModel foodOrderModel) {
        int position = isItemPresent(foodOrderModel);
        if (position > -1) {
            selectedFoodOrderList.remove(position);
        } else {
            selectedFoodOrderList.add(foodOrderModel);
        }

    }

    public void clearItems() {
        selectedFoodOrderList.clear();
    }

    public void calculateLatestCost(){
        int totalCost = 0;
        for (FoodOrderModel orderModel : MyApplication.getSingletonObject().getSelectedFoodOrderList()) {
            totalCost += orderModel.getFoodCost() * orderModel.getFoodCount();
        }
        calculatePrice.onCostChanged(String.format("Rs. %s", String.valueOf(totalCost)));
    }

    private int isItemPresent(FoodOrderModel foodOrderModel) {
        for (int i = 0; i < selectedFoodOrderList.size(); i++) {
            if (selectedFoodOrderList.get(i).getFoodId() == foodOrderModel.getFoodId()) {
                return i;
            }

        }
        return -1;
    }

    public ArrayList<FoodOrderModel> getSelectedFoodOrderList() {
        return selectedFoodOrderList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

}
