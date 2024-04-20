package com.doanchung.transferdataAandB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchung.app1.R;

public class Tranf2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranf2);

        TextView tvProductName = findViewById(R.id.tvProductName);
        EditText edtProductPrice = findViewById(R.id.edtProductPrice);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        //get data from activity 1
        Intent myIntent = getIntent();
        String productName = myIntent.getStringExtra("productName");
        tvProductName.setText(productName);

        btnSubmit.setOnClickListener(v -> {
            String productPrice = edtProductPrice.getText().toString();
            Intent myIntent1 = new Intent();
            myIntent1.putExtra("productName", productName);
            myIntent1.putExtra("productPrice", productPrice);
            setResult(1, myIntent1);
            finish();
        });

    }
}