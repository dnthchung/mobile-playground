package com.doanchung.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchung.app1.R;

public class Screen2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        TextView textView = findViewById(R.id.textView2);
        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);

        //get data from Screen1Activity
        Intent myIntent = getIntent();
        String productName = myIntent.getStringExtra("productName");
        textView.setText(productName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = editText.getText().toString();

                //back data to Screen1Activity
                Intent myIntentBack = new Intent();
                myIntentBack.putExtra("productName", productName);
                myIntentBack.putExtra("money", money);
                //or setResult(RESULT_OK, myIntentBack);
                setResult(10, myIntentBack);
                finish();
            }
        });

    }
}