package com.example.davidson.deenews.fragments;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.davidson.deenews.R;

public class DetailActivity extends AppCompatActivity {

    String description,title,urlToImage,source,time;
    TextView titleTv,descriptionTv,timeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbarDetail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Intent myIntent = getIntent();
        description = myIntent.getStringExtra("news_description");
        title = myIntent.getStringExtra("news_title");
        urlToImage = myIntent.getStringExtra("news_image");
        source = myIntent.getStringExtra("source");
        time = myIntent.getStringExtra("time");

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(source);





        titleTv = findViewById(R.id.detail_title);
        descriptionTv = findViewById(R.id.detail_description);
        timeTv = findViewById(R.id.time);

        titleTv.setText(title);
        descriptionTv.setText(description);
        timeTv.setText(time);


        ImageView imageUrl = findViewById(R.id.imageUrl);
        Glide.with(getApplicationContext()).load(urlToImage).into(imageUrl);

    }
}
