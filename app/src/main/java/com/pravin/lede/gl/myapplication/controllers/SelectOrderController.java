package com.pravin.lede.gl.myapplication.controllers;

import com.pravin.lede.gl.myapplication.models.FoodModel;
import com.pravin.lede.gl.myapplication.models.ISelectOrderModel;
import com.pravin.lede.gl.myapplication.models.SelectOrderModel;

import java.util.ArrayList;

public class SelectOrderController implements ISelectOrderController{

    ISelectOrderModel iSelectOrderModel;

    public SelectOrderController() {

        iSelectOrderModel = new SelectOrderModel();

    }


    @Override
    public ArrayList<FoodModel> getSelectOrderList() {
        return iSelectOrderModel.getSelectOrderListFromService();
    }
}
