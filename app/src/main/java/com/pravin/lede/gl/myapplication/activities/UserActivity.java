package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pravin.lede.gl.myapplication.R;

public class UserActivity extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        String name = getIntent().getStringExtra("name");

        userName = findViewById(R.id.name);
        userName.setText(name);
    }
}
