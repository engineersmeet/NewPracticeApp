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

public class SelectActivityItemAdapter extends RecyclerView.Adapter<SelectActivityItemAdapter.MyHolder> {

    ArrayList<ActivityItems> activityItems = new ArrayList<>();

    public SelectActivityItemAdapter(ArrayList<ActivityItems> activityItems) {
        this.activityItems = activityItems;
    }

    @NonNull
    @Override
    public SelectActivityItemAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_group_item, parent, false);
        return new SelectActivityItemAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectActivityItemAdapter.MyHolder holder, int position) {
        ActivityItems activityItem = activityItems.get(position);
        holder.activityImage.setImageResource(activityItem.getImage());
        holder.activityText.setText(activityItem.getItem());
    }

    @Override
    public int getItemCount() {
        return activityItems.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView activityImage;
        TextView activityText;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            activityImage = itemView.findViewById(R.id.activities_item_image);
            activityText = itemView.findViewById(R.id.activities_item_title);
        }
    }
}
