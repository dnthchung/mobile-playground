package com.chungdoan.mob2041.activities;

import android.os.Bundle;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chungdoan.mob2041.R;
import com.chungdoan.mob2041.adapters.LoaiSachAdapter;
import com.chungdoan.mob2041.daos.LoaiSachDAO;
import com.chungdoan.mob2041.models.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    LoaiSachDAO loaiSachDAO = new LoaiSachDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //UI mapping
        RecyclerView rvListCategory = findViewById(R.id.rvListCategory);
        FloatingActionButton fabAddCategory = findViewById(R.id.fabAddCategory);

        //data
        loaiSachDAO = new LoaiSachDAO(this);
        ArrayList<LoaiSach> loaiSachList = loaiSachDAO.getAllLoaiSach2();

        //adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListCategory.setLayoutManager(linearLayoutManager);

        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(loaiSachList, this);
        rvListCategory.setAdapter(loaiSachAdapter);
    }
}