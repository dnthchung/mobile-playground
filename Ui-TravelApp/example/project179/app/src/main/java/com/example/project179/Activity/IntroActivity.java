package com.example.project179.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project179.databinding.ActivityIntroBinding;


public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startBtn.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this, MainActivity.class)));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}