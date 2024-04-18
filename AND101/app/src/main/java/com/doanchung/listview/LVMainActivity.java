package com.doanchung.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.doanchung.app1.R;

public class LVMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvmain);

        //show student list (mssv, name, avg point)
        /*
        #3 steps:
          1.Layout:
           - Layout chứa ListView
           - layout của item hiển thị lên ListView
          2.data: 10 students

          3.adapter: Adapter
         */

        //1.Layout
        ListView lvStudent = findViewById(R.id.lvStudent);



    }
}