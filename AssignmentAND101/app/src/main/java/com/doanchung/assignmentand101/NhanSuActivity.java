package com.doanchung.assignmentand101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.doanchung.adapter.NhanSuAdapter;
import com.doanchung.model.NhanSu;

import java.util.ArrayList;

public class NhanSuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_su);

        //Giao diện
        ListView lvNhanSu = findViewById(R.id.lvNhanSu);
        //Data
        ArrayList<NhanSu> listNS = new ArrayList<>();
        listNS.add(new NhanSu("NS01", "Nguyen Van A", "PB01"));
        listNS.add(new NhanSu("NS02", "Nguyen Van B", "PB02"));
        listNS.add(new NhanSu("NS03", "Nguyen Van C", "PB03"));
        listNS.add(new NhanSu("NS04", "Nguyen Van D", "PB04"));
        //Adapter
        NhanSuAdapter adapter = new NhanSuAdapter(NhanSuActivity.this, listNS);
        lvNhanSu.setAdapter(adapter);
    }
}