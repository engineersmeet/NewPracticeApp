package com.pravin.lede.gl.myapplication.models;

import com.pravin.lede.gl.myapplication.models.ActivityItems;

import java.util.ArrayList;

public class ActivityModel {

    ArrayList<ActivityItems> activityItems;

    String groupTitle;

    public ActivityModel(ArrayList<ActivityItems> activityItems, String groupTitle) {
        this.activityItems = activityItems;
        this.groupTitle = groupTitle;
    }

    public ArrayList<ActivityItems> getActivityItems() {
        return activityItems;
    }

    public String getGroupTitle() {
        return groupTitle;
    }
}
