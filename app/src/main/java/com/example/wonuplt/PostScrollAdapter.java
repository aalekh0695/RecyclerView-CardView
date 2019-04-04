package com.example.wonuplt;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class PostScrollAdapter extends RecyclerView.Adapter<PostScrollAdapter.MyViewHolder> {
    public List<Thumbnail> thumbnailList;
    public Context context;
    public ArrayList<Integer> colorStatus;

    public PostScrollAdapter(Context context, ArrayList<Integer> colorStatus){
        this.context = context;
//        this.thumbnailList = thumbnailList;
        this.colorStatus = colorStatus;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_scroll_card_content, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
//        Thumbnail thumbnail = thumbnailList.get(position);
        String[] colorsTxt = context.getResources().getStringArray(R.array.colors);
        int newColor = Color.parseColor(colorsTxt[colorStatus.get(position)]);
        myViewHolder.rl_post_scroll_view.setBackgroundColor(newColor);
    }

    @Override
    public int getItemCount() {
        return colorStatus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rl_post_scroll_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rl_post_scroll_view = itemView.findViewById(R.id.rl_post_scroll_view);
        }
    }
}
