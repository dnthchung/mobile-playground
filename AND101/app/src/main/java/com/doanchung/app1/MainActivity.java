package com.doanchung.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anh xa
        EditText edtData = (EditText) findViewById(R.id.edtData);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        TextView tvData = (TextView) findViewById(R.id.tvData);
        //set su kien
        //Đây là một cách viết ngắn gọn và
        //hiện đại hơn được giới thiệu trong Java 8 và
        // được hỗ trợ từ Android API level 24 trở lên.
//        btnSave.setOnClickListener(v -> {
//            String data = edtData.getText().toString();
//            edtData.setHint(data);
//        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = edtData.getText().toString();
                edtData.setHint(data);
                tvData.setText("user input: " + data);
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
    }

}