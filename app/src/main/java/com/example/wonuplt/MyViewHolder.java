package com.example.wonuplt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_title, tv_desc, tv_time, tv_action;
    public ImageButton ib_arrow, ib_timer;
    public LinearLayout ll_post_card;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_desc = itemView.findViewById(R.id.tv_desc);
        tv_time = itemView.findViewById(R.id.tv_time);
        tv_action = itemView.findViewById(R.id.tv_action);
        ib_arrow = itemView.findViewById(R.id.ib_arrow);
        ib_timer = itemView.findViewById(R.id.ib_timer);
        ll_post_card = itemView.findViewById(R.id.ll_post_card);
    }
}

