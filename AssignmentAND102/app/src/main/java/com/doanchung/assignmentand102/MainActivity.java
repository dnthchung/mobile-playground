package com.doanchung.assignmentand102;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anh xa from main_activity.xml
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);

        /**
         * 1. setSupportActionBar(toolbar) -> dùng để thay thế ActionBar mặc định của Activity
         * 2. getSupportActionBar().setDisplayHomeAsUpEnabled(true) -> hiển thị nút back trên toolbar
         * 3. getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu) -> thay đổi icon cho nút back thành icon menu
         */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

    }
}