package com.pravin.lede.gl.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.models.ActivityModel;

import java.util.ArrayList;

public class SelectActivityGroupAdapter extends RecyclerView.Adapter<SelectActivityGroupAdapter.MyHolder> {

    ArrayList<ActivityModel> activities = new ArrayList<>();
    Context context;

    public SelectActivityGroupAdapter(ArrayList<ActivityModel> activities) {
        this.activities = activities;
    }

    @NonNull
    @Override
    public SelectActivityGroupAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_group, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectActivityGroupAdapter.MyHolder holder, int position) {

        ActivityModel activityModel = activities.get(position);

        holder.activityItemRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.activityItemRecyclerView.setAdapter(new SelectActivityItemAdapter(activityModel.getActivityItems()));
        holder.groupTitle.setText(activityModel.getGroupTitle());

    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        RecyclerView activityItemRecyclerView;
        TextView groupTitle;
        ImageView groupActionImage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            activityItemRecyclerView = itemView.findViewById(R.id.activities_group_recycler_view);
            groupTitle = itemView.findViewById(R.id.activities_group_title);
            groupActionImage = itemView.findViewById(R.id.activities_group_action_image);
        }
    }
}
