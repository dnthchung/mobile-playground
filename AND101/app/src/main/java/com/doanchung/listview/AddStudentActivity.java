package com.doanchung.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.doanchung.app1.R;
import com.doanchung.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        EditText edtStudentId = findViewById(R.id.etId);
        EditText edtStudentName = findViewById(R.id.etName);
        EditText edtScore = findViewById(R.id.etScore);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnSubmit.setOnClickListener(v -> {
            //lấy dữ liệu từ 3 edittext
            String studentId = edtStudentId.getText().toString();
            String studentName = edtStudentName.getText().toString();
            float avgPoint = Float.parseFloat(edtScore.getText().toString());
            //truyền dữ liệu về cho main activity
//            //1. tạo intent
//            Intent myIn = new Intent();
//            Bundle bundle = new Bundle();
//
//            //2. put data
//            bundle.putString("studentId", studentId);
//            bundle.putString("studentName", studentName);
//            bundle.putFloat("avgPoint", avgPoint);
//            myIn.putExtras(bundle);
//
//            //3. setResult
//            setResult(10, myIn);
//            //4. finish
//            finish();

            //1. tạo model
            Student studentModel = new Student(studentId, studentName, avgPoint);
            //2. tạo intent
            Intent myIn = new Intent();
            Bundle bundle = new Bundle();
            //3. put data
            bundle.putSerializable("studentNe", studentModel);
            //4. put bundle vào intent
            myIn.putExtras(bundle);
            //5. setResult
            setResult(1, myIn);
            //6. finish
            finish();
        });


    }
}