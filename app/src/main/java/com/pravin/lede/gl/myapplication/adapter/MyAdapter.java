package com.pravin.lede.gl.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pravin.lede.gl.myapplication.models.FoodOrderModel;
import com.pravin.lede.gl.myapplication.models.Info;
import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.activities.UserActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    ArrayList<FoodOrderModel> strings = new ArrayList<>();
    Context context;

    public MyAdapter(ArrayList<FoodOrderModel> strings) {
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_file, parent, false);
        MyHolder myHolder = new MyHolder(listItem);
        context = parent.getContext();
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        final FoodOrderModel orderModel = strings.get(position);

        holder.foodName.setText(orderModel.getFoodModel().getFoodName());
        holder.foodCost.setText(String.valueOf(orderModel.getFoodCost()));

        Picasso.get().load(orderModel.getFoodModel().getFoodImage()).into(holder.foodImage);

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView foodName;
        TextView foodCost;
        ImageView foodImage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.food_order_name);
            foodCost = itemView.findViewById(R.id.food_order_cost);
            foodImage = itemView.findViewById(R.id.food_order_image);

        }
    }
}
