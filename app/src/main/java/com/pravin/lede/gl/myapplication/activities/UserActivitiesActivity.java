package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.models.ActivityItems;
import com.pravin.lede.gl.myapplication.models.ActivityModel;
import com.pravin.lede.gl.myapplication.adapter.ActivitiesAdapter;

import java.util.ArrayList;

public class UserActivitiesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activities);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.myRecyclerView);
        linearLayout = findViewById(R.id.icon_status);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new ActivitiesAdapter(getActivities()));
    }

    private ArrayList<ActivityModel> getActivities() {

        ArrayList<ActivityModel> activityModels = new ArrayList<>();

        ArrayList<ActivityItems> activityItemsZero = new ArrayList<>();
        activityItemsZero.add(new ActivityItems(R.drawable.ic_airport_shuttle_black_24dp, "Shuttle"));
        activityItemsZero.add(new ActivityItems(R.drawable.ic_adjust_black_24dp, "Adjust"));
        activityItemsZero.add(new ActivityItems(R.drawable.ic_arrow_upward_black_24dp, "Upward"));
        activityItemsZero.add(new ActivityItems(R.drawable.ic_beach_access_black_24dp, "Beach"));
        activityItemsZero.add(new ActivityItems(R.drawable.ic_check_circle_black_24dp, "Circle"));

        activityModels.add(new ActivityModel(activityItemsZero, "General"));

        activityItemsZero = new ArrayList<>();

        activityItemsZero.add(new ActivityItems(R.drawable.ic_brightness_4_black_24dp, "Sports"));
        activityItemsZero.add(new ActivityItems(R.drawable.ic_music_note_black_24dp, "Music"));
        activityItemsZero.add(new ActivityItems(R.drawable.ic_tablet_android_black_24dp, "Mobile"));
        activityItemsZero.add(new ActivityItems(R.drawable.ic_airport_shuttle_black_24dp, "Education"));
        activityItemsZero.add(new ActivityItems(R.drawable.ic_arrow_upward_black_24dp, "Swimming"));

        activityModels.add(new ActivityModel(activityItemsZero, "Special"));

        return activityModels;
    }
}
