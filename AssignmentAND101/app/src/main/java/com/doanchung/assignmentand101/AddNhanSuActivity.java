package com.doanchung.assignmentand101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.doanchung.adapter.SpinnerPBAdapter;
import com.doanchung.model.NhanSu;
import com.doanchung.model.PhongBan;

import java.util.ArrayList;

public class AddNhanSuActivity extends AppCompatActivity {

    EditText editTextEmployeeId, editTextEmployeeName;
    Button buttonAddEmployee;
    Spinner spinnerDepartments;
    private String tenPhongBan;

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
                Toast.makeText(AddNhanSuActivity.this, "Selected: " + listPB.get(position).getTenPhongBan(), Toast.LENGTH_SHORT).show();
                tenPhongBan = listPB.get(position).getTenPhongBan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //add nhân sự
        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextEmployeeId.getText().toString();
                String name = editTextEmployeeName.getText().toString();
                String department = listPB.get(spinnerDepartments.getSelectedItemPosition()).getMaPhongBan();

                NhanSu nhanSuMoi = new NhanSu(id, name, department);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("nhanSuMoi", nhanSuMoi);
                intent.putExtras(bundle);
                setResult(1, intent);
                // Thêm nhân sự vào danh sách

                // Đóng màn hình thêm nhân sự
                finish();
            }
        });


    }
}