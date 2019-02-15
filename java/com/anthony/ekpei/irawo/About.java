package com.anthony.ekpei.irawo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class About extends AppCompatActivity {
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mtoolbar=(Toolbar) findViewById(R.id.main_pageappbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("About Us");
    }
}
