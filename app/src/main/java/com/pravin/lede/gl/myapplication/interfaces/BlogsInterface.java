package com.pravin.lede.gl.myapplication.interfaces;

import com.pravin.lede.gl.myapplication.models.BlogsInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BlogsInterface {

    @GET("mobile_blogs")
    Call<List<BlogsInfo>> getBlogs();

}
