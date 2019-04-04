package com.example.wonuplt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private List<PostCard> postCardList;
    private PostCardAdapter postCardAdapter;
    int selectedColor = -10, postCardPos = -10;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String[] title = {"Text from 2 to 40 characters", "In this case it is 40 characters of text",
                                "Short text", "Random text string"};
    private String[] description = {"Some cards will have one line of text",
            "Some cards will have two lines of text. Just like this. This is long text"};
    private Integer[] timer = {R.drawable.ic_timer_red_24dp, R.drawable.ic_timer_white_24dp, R.drawable.ic_timer_gray_24dp};
    private String[] timeZone = {"IST", "EST", "GMT"};
    private String[] action = {"Action taken", "Take some action"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mSwipeRefreshLayout = findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        setSupportActionBar(toolbar);

        postCardList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postCardAdapter = new PostCardAdapter(this, postCardList);
        recyclerView.setAdapter(postCardAdapter);
        prepareData();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 101 && resultCode == RESULT_OK){
            selectedColor = data.getIntExtra("selectedColor", -1);
            postCardPos = data.getIntExtra("postCardPos", -1);
//            Toast.makeText(this, postCardPos+" "+selectedColor, Toast.LENGTH_SHORT).show();

            PostCard card = postCardList.get(postCardPos);
            PostCard newPostcard = new PostCard(card.getTitle(),card.getDesc(),card.getTime(),
                    card.getTimeZoneIndex(),card.getAction(),card.getActionIcon(),selectedColor);
            postCardList.set(postCardPos, newPostcard);
            postCardAdapter.notifyItemChanged(postCardPos);
        }
    }

    private void prepareData() {
        int counter = 0;
        ArrayList<Integer> colorStatus = new ArrayList<>();
        String[] colorsTxt = getResources().getStringArray(R.array.colors);

        Random random = new Random();
        while (counter<25){
            int x = random.nextInt(25);
            if(!colorStatus.contains(x)){
                colorStatus.add(x);
                counter++;
            }
        }

//        Log.i("Home Actviity", colorStatus.toString());
        for(int i=1;i<=25;i++){
            int[] randArray = {random.nextInt(4),random.nextInt(2),random.nextInt(3),
                    random.nextInt(2), colorStatus.get(i-1)};

//            Log.i("Home Actiity", randArray[0]+" "+randArray[1]+" "+randArray[2]+" "+randArray[3]);
            PostCard postCard = new PostCard(title[randArray[0]], description[randArray[1]],
                                    timeZone[randArray[2]],randArray[2], action[randArray[3]], randArray[3],
                                    Color.parseColor(colorsTxt[randArray[4]]));
            postCardList.add(postCard);
        }
        postCardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRefresh() {
        postCardAdapter.clear();
        prepareData();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
