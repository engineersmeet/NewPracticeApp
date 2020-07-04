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
import com.pravin.lede.gl.myapplication.interfaces.ItemSelectionListener;
import com.pravin.lede.gl.myapplication.models.ActivityModel;

import java.util.ArrayList;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.MyHolder> {

    ArrayList<ActivityModel> activityModels = new ArrayList<>();
    ItemSelectionListener itemSelectionListener;
    Context context;
    private View imageView;

    public ActivitiesAdapter(ArrayList<ActivityModel> activityModels, ItemSelectionListener itemSelectionListener) {
        this.activityModels = activityModels;
        this.itemSelectionListener= itemSelectionListener;
    }

    @NonNull
    @Override
    public ActivitiesAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_activity, parent, false);
        return new ActivitiesAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ActivitiesAdapter.MyHolder holder, int position) {

        ActivityModel model = activityModels.get(position);

        holder.textView.setText(model.getGroupTitle());
        holder.activityItemRecyclerView.setAdapter(new ActivityItemAdapter(model.getActivityItems(), itemSelectionListener));


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.imageView.getTag().equals("visible")) {
                    holder.activityItemRecyclerView.setVisibility(View.GONE);
                    holder.imageView.setTag("GONE");
                    holder.imageView.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                } else {
                    holder.activityItemRecyclerView.setVisibility(View.VISIBLE);
                    holder.imageView.setTag("visible");
                    holder.imageView.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return activityModels.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        TextView textView;
        RecyclerView activityItemRecyclerView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.activity_name);
            imageView = itemView.findViewById(R.id.activity_action_image);
            activityItemRecyclerView = itemView.findViewById(R.id.activity_item_recycler_view);
            activityItemRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        }
    }
}
