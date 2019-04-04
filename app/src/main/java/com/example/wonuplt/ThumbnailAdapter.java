package com.example.wonuplt;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.MyViewHolder> {
    public List<Thumbnail> thumbnailList;
    public ArrayList<Integer> colorStatus;
    public Context context;

    public ThumbnailAdapter(Context context, List<Thumbnail> thumbnailList){
        this.context = context;
        this.thumbnailList = thumbnailList;
//        this.colorStatus = colorStatus;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail_card_content, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        final Thumbnail thumbnail = thumbnailList.get(position);

//        String[] colorsTxt = context.getResources().getStringArray(R.array.colors);
//        int newColor = Color.parseColor(colorsTxt[colorStatus.get(position)]);
        myViewHolder.rl_thumnail_view.setBackgroundColor(thumbnail.getColor());
        myViewHolder.rl_thumnail_view.setAlpha(thumbnail.getAlphaVal());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.rl_thumnail_view.setAlpha(1.0f);

                if(context == null)
                    return;
                if(context instanceof ThreadActivity){
                    ThreadActivity threadActivity = (ThreadActivity)context;
                    threadActivity.scrollPost(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return thumbnailList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rl_thumnail_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rl_thumnail_view = itemView.findViewById(R.id.rl_thumnail_view);
        }
    }
}
