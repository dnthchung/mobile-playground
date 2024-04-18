package com.doanchung.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.doanchung.app1.R;
import com.doanchung.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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

        //=============== custom =============
        //Yc: Hiển thị danh sách sản phẩm(tên,giá)
        //giao diện

        //data
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Táo", 20000, R.mipmap.banner3));
        products.add(new Product("Cam", 30000, R.mipmap.banner3));
        products.add(new Product("Xoài", 40000, R.mipmap.banner3));
        products.add(new Product("Chuối", 50000, R.mipmap.banner3));
        products.add(new Product("Nho", 60000, R.mipmap.banner3));
        products.add(new Product("Lê", 70000, R.drawable.ic_launcher_foreground));
        products.add(new Product("Dưa hấu", 80000, R.mipmap.banner3));
        products.add(new Product("Dừa", 90000, R.mipmap.banner3));

        //adapter: simple adapter : must using hashmap
        //convert list to hashmap (key : String , value : Object means value can be any type)
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Product product : products) {
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("nameNe", product.getName());
            hm.put("priceNe", product.getPrice());
            hm.put("imageNe", product.getImage());
            listHM.add(hm);
        }
        //giải thích các parameter
        //context, data, layout, key(quyết định xem giá trị nào sẽ dc hiển thị), id widget (quyết định vị trí của dữ liệu trên)
        SimpleAdapter adapter = new SimpleAdapter(
                MainListviewActivity.this,
                listHM,
                R.layout.listview_items1,
                new String[]{"nameNe", "priceNe","imageNe"},
                new int[]{R.id.tvName, R.id.tvPrice,R.id.ivImage}
        );
        lvHoaQua.setAdapter(adapter);
    }
}
