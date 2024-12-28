package com.example.project179.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.project179.Domain.PropertyDomain;
import com.example.project179.R;
import com.example.project179.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    private PropertyDomain object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        getBundles();
        setVariable();

    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
        int drawableResourceID=getResources().getIdentifier(object.getPickPath(),"drawable",DetailActivity.this.getPackageName());

        Glide.with(DetailActivity.this)
                .load(drawableResourceID)
                .into(binding.picDetail);

        binding.titleAddressTxt.setText(object.getTitle()+" in "+object.getAddress());
        binding.typeTxt.setText(object.getType());
        binding.descriptionTxt.setText(object.getDescription());
        binding.priceTxt.setText("$"+object.getPrice());
        binding.bedTxt.setText(object.getBed()+" Bedroom");
        binding.bathTxt.setText(object.getBath()+" Bathroom");
        binding.sizeTxt.setText(object.getSize()+" m2");

        if(object.isGarage()){
            binding.garageTxt.setText("Car Garage");
        }else{
            binding.garageTxt.setText("no-Car Garage");
        }
    }

    private void getBundles() {
       object= (PropertyDomain) getIntent().getSerializableExtra("object");
    }
}