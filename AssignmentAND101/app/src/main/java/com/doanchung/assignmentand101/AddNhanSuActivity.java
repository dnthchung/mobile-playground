package com.doanchung.assignmentand101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.doanchung.adapter.SpinnerPBAdapter;
import com.doanchung.model.PhongBan;

import java.util.ArrayList;

public class AddNhanSuActivity extends AppCompatActivity {

    EditText editTextEmployeeId, editTextEmployeeName;
    Button buttonAddEmployee;
    Spinner spinnerDepartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nhan_su);

        // Ánh xạ các thành phần trong giao diện
        editTextEmployeeId = findViewById(R.id.editTextEmployeeId);
        editTextEmployeeName = findViewById(R.id.editTextEmployeeName);
        spinnerDepartments = findViewById(R.id.spinnerDepartments);

        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        //set adapter cho spinner
        ArrayList<PhongBan> listPB = new PhongBanActivity().getListPB();
        SpinnerPBAdapter adapter = new SpinnerPBAdapter(this, listPB);

        // Xử lý sự kiện khi người dùng nhấn vào nút "Thêm nhân sự"
        spinnerDepartments.setAdapter(adapter);

        spinnerDepartments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}