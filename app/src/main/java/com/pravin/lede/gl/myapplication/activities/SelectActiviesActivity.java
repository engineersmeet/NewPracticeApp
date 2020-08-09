package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.adapter.SelectActivityGroupAdapter;
import com.pravin.lede.gl.myapplication.models.ActivityItems;
import com.pravin.lede.gl.myapplication.models.ActivityModel;

import java.util.ArrayList;

public class SelectActiviesActivity extends AppCompatActivity {

    RecyclerView activityGroupRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_activies);
        init();
    }

    private void init() {

        activityGroupRecyclerView = findViewById(R.id.activities_group_recycler_view);

        activityGroupRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        activityGroupRecyclerView.setAdapter(new SelectActivityGroupAdapter(getActivities()));

    }

    private ArrayList<ActivityModel> getActivities() {

        ArrayList<ActivityModel> activityModels = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            ArrayList<ActivityItems> activityItems = new ArrayList<>();
            activityItems.add(new ActivityItems(R.drawable.ic_airport_shuttle_black_24dp, "Shuttle"));
            activityItems.add(new ActivityItems(R.drawable.ic_adjust_black_24dp, "Adjust"));
            activityItems.add(new ActivityItems(R.drawable.ic_arrow_upward_black_24dp, "Upward"));
            activityItems.add(new ActivityItems(R.drawable.ic_beach_access_black_24dp, "Beach"));
            activityItems.add(new ActivityItems(R.drawable.ic_check_circle_black, "Circle"));

            activityModels.add(new ActivityModel(activityItems, "General"));
        }

        return activityModels;
    }
}
