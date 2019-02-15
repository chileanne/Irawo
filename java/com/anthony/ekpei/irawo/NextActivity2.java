package com.anthony.ekpei.irawo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NextActivity2 extends AppCompatActivity {
    private TextView tiles,overviewss,realsedates;
    private ImageView image;
    private String titles,overviews,images,realease,a;
    private Toolbar mtoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next2);
        mtoolbar=(Toolbar) findViewById(R.id.main_pageappbar);
        setSupportActionBar(mtoolbar);
        tiles=(TextView) findViewById(R.id.filmtitles);
        overviewss=(TextView) findViewById(R.id.filmoverviecontents);
        image=(ImageView) findViewById(R.id.filmimages);
        realsedates=(TextView) findViewById(R.id.filmreleasedates);

        Intent i= getIntent();
        titles=i.getExtras().getString("TITLE");
        images=i.getExtras().getString("IMAGES");
        overviews=i.getExtras().getString("OVERVIEW");
        realease=i.getExtras().getString("DATE");
        a="FirstAirDate: "+realease;

        getSupportActionBar().setTitle(titles);
        Glide.with(this).load(images).placeholder(R.drawable.bv).into(image);
        tiles.setText(titles);
        overviewss.setText(overviews);
        realsedates.setText(a);
    }
}
