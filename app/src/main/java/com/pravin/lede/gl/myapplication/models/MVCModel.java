package com.pravin.lede.gl.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class MVCModel implements IMVCModel {

    @Override
    public List<String> getNamesList() {
        List<String> namesList = new ArrayList<>();
        namesList.add("Sonali");
        namesList.add("Priyanka");
        namesList.add("Pravin");
        return namesList;
    }


}
