package com.doanchung.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvUsername = findViewById(R.id.tvUsername);
        TextView tvPassword = findViewById(R.id.tvPassword);

        //get data from another activity - intent
        Intent myIntent = getIntent();

        String data1 = myIntent.getStringExtra("username");
        String data2 = myIntent.getStringExtra("password");
        int oldNum = myIntent.getIntExtra("old", 0);

        //get data from bundle
        Bundle bundle = myIntent.getBundleExtra("myBundle");
        String data3 = bundle.getString("username");
        String data4 = bundle.getString("password");
        int data5 = bundle.getInt("age");

        tvUsername.setText(data1);
        tvPassword.setText(data2);
    }
}