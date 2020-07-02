package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.adapter.MyAdapter;
import com.pravin.lede.gl.myapplication.models.Info;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.myRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new MyAdapter(getData()));
    }

    ArrayList<Info> getData(){

        ArrayList<Info> info = new ArrayList<>();
        info.add(new Info("Sonali", "20 May"));
        info.add(new Info("Priyanka", "7 sep"));
        info.add(new Info("Pravin", "7 sep"));

        return info;
    }
}
