package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.controllers.IMVCController;
import com.pravin.lede.gl.myapplication.controllers.MVCController;

public class MVCActivity extends AppCompatActivity {

    TextView nameTextView;
    TextView nextTextView;

    IMVCController imvcController;

    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);

        imvcController = new MVCController();// Reference, object initialization

        init();

    }

    private void init() {
        nameTextView = findViewById(R.id.name_mvc_activity);
        nextTextView = findViewById(R.id.next_mvc_activity);

        nextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                nameTextView.setText(imvcController.getNameByIndex(index));
            }
        });
    }
}
