package com.doanchung.assignmentand101;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.doanchung.adapter.NhanSuAdapter;
import com.doanchung.model.NhanSu;

import java.util.ArrayList;

public class NhanSuActivity extends AppCompatActivity {

    ArrayList<NhanSu> listNS;
    ListView lvNhanSu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_su);

        //Giao diện
        lvNhanSu = findViewById(R.id.lvNhanSu);
        Button btnAddNS = findViewById(R.id.btnAddNs);

        //Data
        listNS = new ArrayList<>();
        listNS.add(new NhanSu("NS01", "Nguyen Van A", "PB01"));
        listNS.add(new NhanSu("NS02", "Nguyen Van B", "PB02"));
        listNS.add(new NhanSu("NS03", "Nguyen Van C", "PB03"));
        listNS.add(new NhanSu("NS04", "Nguyen Van D", "PB04"));
        //Adapter
        loadDataShow(listNS);

        //Solve search view
        SearchView svNhanSu = findViewById(R.id.svNhanSu);
        svNhanSu.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //search
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //search
                ArrayList<NhanSu> listNSFilter = new ArrayList<>();
                for(NhanSu ns : listNS) {
                    if(ns.getTenNhanVien() != null && ns.getTenNhanVien().toLowerCase().contains(newText.toLowerCase())) {
                        listNSFilter.add(ns);
                    }
                }
                loadDataShow(listNSFilter);
                return false;
            }
        });

        //add new nhan su
        btnAddNS.setOnClickListener(v -> {
            //show dialog
            Intent intent = new Intent(NhanSuActivity.this, AddNhanSuActivity.class);
            myLauncher.launch(intent);
        });

    }

    private ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == 1) {
                    Intent intent = result.getData();
                    Bundle bundle = intent.getExtras();
                    NhanSu ns = (NhanSu) bundle.getSerializable("nhanSuMoi");
                    listNS.add(ns);
                    loadDataShow(listNS);
                }
            });

    private void loadDataShow(ArrayList<NhanSu> listNS) {
        NhanSuAdapter adapter = new NhanSuAdapter(NhanSuActivity.this, listNS);
        lvNhanSu.setAdapter(adapter);
    }
}