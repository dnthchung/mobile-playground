package com.doanchung.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.doanchung.app1.R;

public class MainListviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listview);

        // Ánh xạ ListView từ layout
        ListView lvHoaQua = (ListView) findViewById(R.id.lvHoaQua);

        // Dữ liệu hiển thị lên ListView
//        String[] tenHoaQua = {"Tao", "Cam", "Xoai", "Chuoi", "Nho", "Le", "Dua hau", "Dua"};

        // Tạo adapter
        //tham số: context(chỗ nào gọi adapter) , layout giao diện cho từng dòng trong listview, id widget trong layout,  mảng dữ liệu
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                MainListviewActivity.this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                tenHoaQua
//        );
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                MainListviewActivity.this,
//                R.layout.listview_items1,
//                R.id.tvName,
//                tenHoaQua
//        );

        // Set adapter cho ListView
//        lvHoaQua.setAdapter(adapter);
    }
}
