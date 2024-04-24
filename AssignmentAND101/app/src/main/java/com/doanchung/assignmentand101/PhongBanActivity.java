package com.doanchung.assignmentand101;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import com.doanchung.adapter.PhongBanAdapter;
import com.doanchung.model.PhongBan;
import java.util.ArrayList;

public class PhongBanActivity extends AppCompatActivity {
    ListView lvPhongBan;
    ArrayList<PhongBan> listPB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);

        lvPhongBan = findViewById(R.id.lvPhongBan);

        //1. giao diện
        // - chứa listview - ok - đẩy sang toàn cục
        // - của từng item hiển thị lên listview - ok

        //2. data

        //3. adapter
        loadDataShow(getListPB());

        //solve search view
        SearchView svPhongBan = findViewById(R.id.svPhongBan);
        svPhongBan.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //search
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //search
                ArrayList<PhongBan> listPBFilter = new ArrayList<>();
                for(PhongBan pb : getListPB()) {
                    if(pb.getTenPhongBan() != null && pb.getTenPhongBan().toLowerCase().contains(newText.toLowerCase())) {
                        listPBFilter.add(pb);
                    }
                }
                loadDataShow(listPBFilter);
                return false;
            }
        });
    }

    private void loadDataShow(ArrayList<PhongBan> listNeedToBeLoadToListView) {
        PhongBanAdapter adapter = new PhongBanAdapter(PhongBanActivity.this, listNeedToBeLoadToListView);
        lvPhongBan.setAdapter(adapter);
    }

    public ArrayList<PhongBan> getListPB() {
        listPB = new ArrayList<>();
        listPB.add(new PhongBan("PB01", "Phòng ban 01"));
        listPB.add(new PhongBan("PB02", "Phòng ban 02"));
        listPB.add(new PhongBan("PB03", "Phòng ban 03"));
        listPB.add(new PhongBan("PB04", "Phòng ban 04"));
        return listPB;
    }
}
