package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.adapter.BlogsAdapter;
import com.pravin.lede.gl.myapplication.interfaces.BlogsInterface;
import com.pravin.lede.gl.myapplication.models.BlogsInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        recyclerView=findViewById(R.id.blogs_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(new BlogsAdapter(new ArrayList<>()));

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://prointellects.com/welcome/")
                .addConverterFactory(GsonConverterFactory.create()).
                        build();

        BlogsInterface blogsInterface = retrofit.create(BlogsInterface.class);

        Call<List<BlogsInfo>> call = blogsInterface.getBlogs();

        call.enqueue(new Callback<List<BlogsInfo>>() {
            @Override
            public void onResponse(Call<List<BlogsInfo>> call, Response<List<BlogsInfo>> response) {

                ArrayList<BlogsInfo> blogsInfos = (ArrayList<BlogsInfo>) response.body();
                recyclerView.setAdapter(new BlogsAdapter(blogsInfos));

                Toast.makeText(UserActivity.this, "Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<List<BlogsInfo>> call, Throwable t) {
                Toast.makeText(UserActivity.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
