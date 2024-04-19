package com.doanchung.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.doanchung.app1.R;
import com.doanchung.model.Student;

import java.util.ArrayList;

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

        //2.data
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("SV001", "Nguyen Van A", 8.5f));
        students.add(new Student("SV002", "Nguyen Van B", 7.5f));
        students.add(new Student("SV003", "Nguyen Van C", 6.5f));
        students.add(new Student("SV004", "Nguyen Van D", 5.5f));
        students.add(new Student("SV005", "Nguyen Van E", 4.5f));
        students.add(new Student("SV006", "Nguyen Van F", 3.5f));

        //3. adapter - cầu nối data với listView

    }
}