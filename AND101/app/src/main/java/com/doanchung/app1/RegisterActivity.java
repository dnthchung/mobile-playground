package com.doanchung.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button back2 = findViewById(R.id.back2);

        back2.setOnClickListener(v -> {
            //nó sẽ destroy luôn cái activity này để
            // trở về activity cũ do thằng ac mới đang đè lên thằng ac cũ
            finish();
        });

    }
}