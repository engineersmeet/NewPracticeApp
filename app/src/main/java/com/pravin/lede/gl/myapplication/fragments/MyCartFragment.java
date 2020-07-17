package com.pravin.lede.gl.myapplication.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pravin.lede.gl.myapplication.MyApplication;
import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.adapter.MyAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment Activity", "Fragment Cart Resumed");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment Activity", "Fragment Cart Paused");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Fragment Activity", "Fragment Cart created");

        View view=inflater.inflate(R.layout.fragment_my_cart, container, false);
        init(view);
        return view;
    }

    private void init(View viewInit) {
        recyclerView = viewInit.findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new MyAdapter(MyApplication.getSingletonObject().getSelectedFoodOrderList());
        recyclerView.setAdapter(myAdapter);
    }

    public void updateCartAdapter(){
        myAdapter.updateAdapter(MyApplication.getSingletonObject().getSelectedFoodOrderList());
    }

}
