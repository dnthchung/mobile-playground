package com.doanchung.assignmentand101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPhongBan = findViewById(R.id.btnPhongBan);
        Button btnNhanSu = findViewById(R.id.btnNhanSu);
        Button btnExist = findViewById(R.id.btnExist);

        btnPhongBan.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PhongBanActivity.class);
            startActivity(intent);
        });

        btnNhanSu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NhanSuActivity.class);
            startActivity(intent);
        });

    }
}