package com.pravin.lede.gl.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.models.FoodModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyHolder> {
    ArrayList<FoodModel> foodModels = new ArrayList<>();

    public FoodAdapter(ArrayList<FoodModel> foodModels) {
        this.foodModels = foodModels;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.foodName.setText(foodModels.get(position).getFoodName());
        holder.foodDesc.setText(foodModels.get(position).getFoodDesc());
        holder.foodVotes.setText(String.format("%s Votes", foodModels.get(position).getFoodVotes()));
        holder.foodRating.setRating(Integer.valueOf(foodModels.get(position).getFoodRating()));
        holder.foodCost.setText(String.format("Rs. %s", foodModels.get(position).getFoodCost()));
        Picasso.get().load(foodModels.get(position).getFoodImage()).into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        TextView foodDesc;
        TextView foodVotes;
        RatingBar foodRating;
        TextView foodCost;
        ImageView foodImage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodDesc = itemView.findViewById(R.id.food_desc);
            foodVotes = itemView.findViewById(R.id.food_votes);
            foodRating = itemView.findViewById(R.id.food_rating);
            foodCost = itemView.findViewById(R.id.food_cost);
            foodImage = itemView.findViewById(R.id.food_image);
        }
    }
}
