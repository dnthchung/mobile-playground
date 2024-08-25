package com.chungdoan.mob2041.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chungdoan.mob2041.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapping
        LinearLayout layoutManageCategory = findViewById(R.id.layoutManageCategory);
        LinearLayout layoutManageBooks = findViewById(R.id.layoutManageBooks);

        layoutManageCategory.setOnClickListener(v -> {
            //go to manage category
            startActivity(
                    new Intent(MainActivity.this, CategoryActivity.class)
            );
        });

        layoutManageBooks.setOnClickListener(v -> {
            //go to manage books
            startActivity(
                    new Intent(MainActivity.this, BookActivity.class)
            );
        });




    }
}