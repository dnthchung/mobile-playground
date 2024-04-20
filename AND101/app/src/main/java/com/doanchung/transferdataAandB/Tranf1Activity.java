package com.doanchung.transferdataAandB;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchung.app1.R;

public class Tranf1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranf1);

        EditText edtProductName = findViewById(R.id.edtProductName);
        TextView tvProductPrice = findViewById(R.id.tvProductPrice);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Tranf1Activity.this, Tranf2Activity.class);
                String productName = edtProductName.getText().toString();
                myIntent.putExtra("productName", productName);
//                startActivity(myIntent);
                myLauncher.launch(myIntent);
            }
        });

    }

    private ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if (o.getResultCode() == 1) {
                        Intent data = o.getData();
                        String productName = data.getStringExtra("productName");
                        String productPrice = data.getStringExtra("productPrice");
                        EditText edtProductName = findViewById(R.id.edtProductName);
                        edtProductName.setText(productName);

                        TextView edtProductPrice = findViewById(R.id.tvProductPrice);
                        edtProductPrice.setText(productPrice);
                    }
                }
            }
    );

}