package com.doanchung.listview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.doanchung.adapter.StudentAdapter;
import com.doanchung.app1.R;
import com.doanchung.model.Student;

import java.util.ArrayList;

public class LVMainActivity extends AppCompatActivity {
    ArrayList<Student> students ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvmain);

        //show student list (mssv, name, avg point)
        //1.Layout
        ListView lvStudent = findViewById(R.id.lvStudent);

        //2.data
        students = new ArrayList<>();
        students.add(new Student("SV001", "Nguyen Van A", 8.5f));
        students.add(new Student("SV002", "Nguyen Van B", 7.5f));
        students.add(new Student("SV003", "Nguyen Van C", 6.5f));
        students.add(new Student("SV004", "Nguyen Van D", 5.5f));
        students.add(new Student("SV005", "Nguyen Van E", 4.5f));
        students.add(new Student("SV006", "Nguyen Van F", 3.5f));

        //3. adapter - cầu nối data với listView
        StudentAdapter adapter = new StudentAdapter(LVMainActivity.this, students);
        lvStudent.setAdapter(adapter);

        //4. feat: thêm sinh viên
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            //chuyển sang màn hình thêm sinh viên
            Intent myIn = new Intent(LVMainActivity.this, AddStudentActivity.class);
            myLauncher.launch(myIn);
        });
    }
    private ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    //xử lý data trả về
                    if (o.getResultCode() == 1) {
                        Intent data = o.getData();
                        Bundle bundle = data.getExtras();
                        //must cast Serializable to Student
                        Student student = (Student) bundle.getSerializable("studentNe");
                        //add student vào list
                        //1.Layout
                        ListView lvStudent = findViewById(R.id.lvStudent);
                        //2.data
                        students.add(student);
                        //3. adapter - cầu nối data với listView - load list again = call adapter again
                        StudentAdapter adapter = new StudentAdapter(LVMainActivity.this, students);
                        lvStudent.setAdapter(adapter);
                    }
                }
            }
    );
}