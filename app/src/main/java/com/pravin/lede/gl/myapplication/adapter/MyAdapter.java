package com.pravin.lede.gl.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pravin.lede.gl.myapplication.models.Info;
import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.activities.UserActivity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    ArrayList<Info> strings = new ArrayList<>();
    Context context;

    public MyAdapter( ArrayList<Info> strings) {
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.item_file, parent, false);
            MyHolder myHolder = new MyHolder(listItem);
            context = parent.getContext();
            return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        final Info info = strings.get(position);

        holder.name.setText(info.getName());
        holder.dob.setText(info.getDob());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("name", info.getName());
                context.startActivity(intent);

               // Toast.makeText(context, "Clicked on item " + info.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView dob;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            dob = itemView.findViewById(R.id.item_dob);
        }
    }
}
