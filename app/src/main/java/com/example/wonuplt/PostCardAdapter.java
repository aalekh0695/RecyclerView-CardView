package com.example.wonuplt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class PostCardAdapter extends RecyclerView.Adapter<MyViewHolder> {
    public List<PostCard> postCardList;
    public Context context;

    public PostCardAdapter(Context context, List<PostCard> postCardList){
        this.context = context;
        this.postCardList = postCardList;
    }

    public void clear(){
        postCardList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<PostCard> list) {
        postCardList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        final PostCard card = postCardList.get(position);

        myViewHolder.tv_title.setText(card.getTitle());
        myViewHolder.tv_desc.setText(card.getDesc());
        myViewHolder.tv_time.setText(card.getTime());
        myViewHolder.tv_action.setText(card.getAction());

//        String[] colorsTxt = context.getResources().getStringArray(R.array.colors);
//        int newColor = Color.parseColor(colorsTxt[card.getColorPos()]);
        myViewHolder.ll_post_card.setBackgroundColor(card.getBackgroundColor());

        if(card.getActionIcon() == 0){
            myViewHolder.ib_arrow.setVisibility(View.GONE);
        }else{
            myViewHolder.ib_arrow.setVisibility(View.VISIBLE);
        }

        switch (card.getTimeZoneIndex()){
            case 0:
                myViewHolder.tv_time.setTextColor(Color.parseColor("#f70029"));
                myViewHolder.ib_timer.setImageResource(R.drawable.ic_timer_red_24dp);
                break;
            case 1:
                myViewHolder.tv_time.setTextColor(Color.parseColor("#ffffff"));
                myViewHolder.ib_timer.setImageResource(R.drawable.ic_timer_white_24dp);
                break;
            case 2:
                myViewHolder.tv_time.setTextColor(Color.parseColor("#9e9e9e"));
                myViewHolder.ib_timer.setImageResource(R.drawable.ic_timer_gray_24dp);
                break;
        }

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context == null)
                    return;
                if(context instanceof HomeActivity){
                    Intent intent = new Intent(context, ThreadActivity.class);
                    intent.putExtra("postcard", card);
                    intent.putExtra("postCardPos", position);
                    ((HomeActivity) context).startActivityForResult(intent, 101);
                }

//                Intent intent = new Intent(context, ThreadActivity.class);
//                intent.putExtra("postcard", card);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postCardList.size();
    }

//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView tv_title, tv_desc, tv_time, tv_action;
//        public ImageButton ib_arrow,ib_timer;
//        public LinearLayout ll_post_card;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tv_title = itemView.findViewById(R.id.tv_title);
//            tv_desc = itemView.findViewById(R.id.tv_desc);
//            tv_time = itemView.findViewById(R.id.tv_time);
//            tv_action = itemView.findViewById(R.id.tv_action);
//            ib_arrow = itemView.findViewById(R.id.ib_arrow);
//            ib_timer = itemView.findViewById(R.id.ib_timer);
//            ll_post_card = itemView.findViewById(R.id.ll_post_card);
//        }
//    }
}
