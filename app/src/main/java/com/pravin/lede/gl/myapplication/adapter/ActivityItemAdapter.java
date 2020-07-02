package com.pravin.lede.gl.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.models.ActivityItems;

import java.util.ArrayList;

public class ActivityItemAdapter extends RecyclerView.Adapter<ActivityItemAdapter.MyHolder> {

    ArrayList<ActivityItems> activityItems = new ArrayList<>();

    public ActivityItemAdapter(ArrayList<ActivityItems> activityItems) {
        this.activityItems = activityItems;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        return new ActivityItemAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.imageView.setImageResource(activityItems.get(position).getImage());
        holder.textView.setText(activityItems.get(position).getItem());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return activityItems.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.activity_item_name);
            imageView = itemView.findViewById(R.id.activity_item_image);
        }
    }
}
