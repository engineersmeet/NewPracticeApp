package com.pravin.lede.gl.myapplication;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.pravin.lede.gl.myapplication.models.FoodOrderModel;

import java.util.ArrayList;

public class MyApplication extends Application {

    private static MyApplication myApplication = null;
    private ArrayList<FoodOrderModel> selectedFoodOrderList = new ArrayList<>();

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

}
