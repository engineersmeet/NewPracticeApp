package com.pravin.lede.gl.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.models.BlogsInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.MyHolder>
{
    ArrayList<BlogsInfo> blogsInfoArrayList = new ArrayList<>();

    public BlogsAdapter(ArrayList<BlogsInfo> blogsInfoArrayList) {
        this.blogsInfoArrayList = blogsInfoArrayList;
    }

    @NonNull
    @Override
    public BlogsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate((R.layout.item_blogs),parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.btitle.setText(blogsInfoArrayList.get(position).getTitle());
        holder.bdescription.setText(blogsInfoArrayList.get(position).getDescription());
        holder.bdate.setText(blogsInfoArrayList.get(position).getDate_time());
        holder.bkeyword.setText(blogsInfoArrayList.get(position).getTitle());
        Picasso.get().load(blogsInfoArrayList.get(position).getImage()).into(holder.bimage);
       // holder.bimage.setxt(blogsInfoArrayList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return blogsInfoArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView btitle;
        TextView bdescription;
        TextView bdate;
        TextView bkeyword;
        ImageView bimage;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            btitle=itemView.findViewById(R.id.blog_title);
            bdescription=itemView.findViewById(R.id.blog_description);
            bdate=itemView.findViewById(R.id.blog_date);
            bkeyword=itemView.findViewById(R.id.blog_keyword);
            bimage=itemView.findViewById(R.id.blog_image);
        }
    }
}
