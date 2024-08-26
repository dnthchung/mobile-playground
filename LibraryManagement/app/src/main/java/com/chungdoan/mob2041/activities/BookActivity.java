package com.chungdoan.mob2041.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chungdoan.mob2041.R;
import com.chungdoan.mob2041.adapters.SachAdapter;
import com.chungdoan.mob2041.daos.SachDAO;
import com.chungdoan.mob2041.models.Sach;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //Ui mapping
        RecyclerView rvListBook = findViewById(R.id.rvListBook);

        //data
        SachDAO sachDAO = new SachDAO(this);
        ArrayList<Sach> sachList = sachDAO.getAllBooks();

        //adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvListBook.setLayoutManager(linearLayoutManager);

        SachAdapter sachAdapter = new SachAdapter(sachList,this);
        rvListBook.setAdapter(sachAdapter);



    }
}