package com.pravin.lede.gl.myapplication.models;

public class FoodModel {

    int id;
    String foodName;
    String foodDesc;
    String foodVotes;
    String foodRating;
    String foodCost;
    String foodImage;

    public FoodModel(int id, String foodName, String foodDesc, String foodVotes, String foodRating, String foodCost, String foodImage) {
        this.id = id;
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodVotes = foodVotes;
        this.foodRating = foodRating;
        this.foodCost = foodCost;
        this.foodImage = foodImage;
    }

    public int getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public String getFoodVotes() {
        return foodVotes;
    }

    public String getFoodRating() {
        return foodRating;
    }

    public String getFoodCost() {
        return foodCost;
    }

    public String getFoodImage() {
        return foodImage;
    }
    //SELECT `id`, `foodName`, `foodDesc`, `foodVotes`, `foodRating`, `foodCost`, `foodImage` FROM `pi_food` WHERE 1
}
