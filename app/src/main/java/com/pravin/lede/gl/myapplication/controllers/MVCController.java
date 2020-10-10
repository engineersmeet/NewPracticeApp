package com.pravin.lede.gl.myapplication.controllers;

import com.pravin.lede.gl.myapplication.models.IMVCModel;
import com.pravin.lede.gl.myapplication.models.MVCModel;

public class MVCController implements IMVCController{

    IMVCModel imvcModel;

    public MVCController() {
        imvcModel = new MVCModel();
    }

    @Override
    public String getNameByIndex(int index) {
        if( index > 2){
            return "OutOfIndex Exception";
        }
        return imvcModel.getNamesList().get(index);
    }

}
