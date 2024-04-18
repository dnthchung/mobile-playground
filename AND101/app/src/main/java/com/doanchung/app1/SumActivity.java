package com.doanchung.app1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SumActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);

        EditText edtNumber1 = (EditText) findViewById(R.id.edtNumber1);
        EditText edtNumber2 = (EditText) findViewById(R.id.edtNumber2);
        Button btnSum = (Button) findViewById(R.id.btnSum);
        TextView tvResult = (TextView) findViewById(R.id.tvResult);

        //radio
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        //checkbox
        CheckBox read = findViewById(R.id.read);
        CheckBox swim = findViewById(R.id.swim);

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number1 = Integer.parseInt(edtNumber1.getText().toString());
                int number2 = Integer.parseInt(edtNumber2.getText().toString());
                int result = number1 + number2;

                int idCheck = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(idCheck);
                String gender = radioButton.getText().toString();

                StringBuilder dataCheckBox = new StringBuilder();
                if (read.isChecked()) {
                    dataCheckBox.append(read.getText().toString()).append(" ");
                }
                if (swim.isChecked()) {
                    dataCheckBox.append(swim.getText().toString()).append(" ");
                }

                tvResult.setText(gender + " " + "hobby: " + dataCheckBox.toString());
            }
        });
    }
}