package com.doanchung.assignmentand101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.doanchung.adapter.PhongBanAdapter;
import com.doanchung.model.PhongBan;

import java.util.ArrayList;

public class PhongBanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);

        //giao diện
        //chứa listview - ok
        ListView lvPhongBan = findViewById(R.id.lvPhongBan);
        //của từng item hiển thị lên listview - ok

        //data
        ArrayList<PhongBan> listPB = new ArrayList<>();
        listPB.add(new PhongBan("PB01", "Phòng ban 01"));
        listPB.add(new PhongBan("PB02", "Phòng ban 02"));
        listPB.add(new PhongBan("PB03", "Phòng ban 03"));
        listPB.add(new PhongBan("PB04", "Phòng ban 04"));

        //adapter
        PhongBanAdapter adapter = new PhongBanAdapter(PhongBanActivity.this, listPB);
        lvPhongBan.setAdapter(adapter);

    }
}