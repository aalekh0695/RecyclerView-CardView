package com.example.wonuplt;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadActivity extends AppCompatActivity {
    int colorPos = 0, finalColorPos = -1, postCardPos = -10;
    boolean colorSelected = false;
    private RecyclerView recycler_view_thumbnail, recycler_view_post_scroll;
    private List<Thumbnail> thumbnailList;
    private ThumbnailAdapter thumbnailAdapter;
    private PostScrollAdapter postScrollAdapter;
    TextView tv_select_color;
    PostCard card;
    ArrayList<Integer> colorStatus;

    public void selectColor(View view){
        if(!colorSelected){
            colorSelected = true;
            finalColorPos = colorPos;
            tv_select_color.setText("This color selected!");
//            Toast.makeText(this, finalColorPos+" ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        tv_select_color = findViewById(R.id.tv_select_color);
        Toolbar toolbar = findViewById(R.id.toolbar_activity_thread);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        postCardPos = intent.getIntExtra("postCardPos", -1);
        card = (PostCard) bundle.getSerializable("postcard");
        Log.i("Thread Activity", card.getTitle());

//        toolbar.setTitle(card.getTitle());
        getSupportActionBar().setTitle(card.getTitle());

        thumbnailList = new ArrayList<>();
        colorStatus = new ArrayList<>();
        recycler_view_thumbnail = findViewById(R.id.recycler_view_thumbnail);
        recycler_view_thumbnail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        thumbnailAdapter = new ThumbnailAdapter(this, thumbnailList);
        recycler_view_thumbnail.setAdapter(thumbnailAdapter);

        recycler_view_post_scroll = findViewById(R.id.recycler_view_post_scroll);
        recycler_view_post_scroll.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        postScrollAdapter = new PostScrollAdapter(this, colorStatus);
        recycler_view_post_scroll.setAdapter(postScrollAdapter);

        prepareData();

        String[] colorsTxt = getResources().getStringArray(R.array.colors);
        for(int i=0;i<colorStatus.size();i++){
            int tappedColor = Color.parseColor(colorsTxt[colorStatus.get(i)]);
            if(tappedColor == card.getBackgroundColor()){
                recycler_view_thumbnail.scrollToPosition(i);
                recycler_view_post_scroll.scrollToPosition(i);
                Thumbnail thumbnail = new Thumbnail(card.getBackgroundColor(), 1.0f);
                thumbnailList.set(i, thumbnail);
                thumbnailAdapter.notifyItemChanged(i);
                break;
            }
        }




    }

    @Override
    public boolean onSupportNavigateUp() {
        String[] colorsTxt = getResources().getStringArray(R.array.colors);
        if(finalColorPos!=-1){
            int selectedColor = Color.parseColor(colorsTxt[colorStatus.get(finalColorPos)]);
            Intent intent = new Intent();
            intent.putExtra("selectedColor", selectedColor);
            intent.putExtra("postCardPos", postCardPos);
            setResult(RESULT_OK, intent);
        }

        finish();
//        onBackPressed();
        return true;
    }

    private void prepareData() {
        int counter = 0;
        String[] colorsTxt = getResources().getStringArray(R.array.colors);

        Random random = new Random();
        while (counter<25){
            int x = random.nextInt(25);
            if(!colorStatus.contains(x)){
                colorStatus.add(x);
                counter++;
            }
        }
        for(int i=0;i<colorStatus.size();i++){
            int myColor = Color.parseColor(colorsTxt[colorStatus.get(i)]);
            Thumbnail thumbnail = new Thumbnail(myColor, 0.2f);
            thumbnailList.add(thumbnail);
        }

        thumbnailAdapter.notifyDataSetChanged();
    }

    public void scrollPost(int position){
        recycler_view_post_scroll.scrollToPosition(position);
        colorPos = position;
    }
}
