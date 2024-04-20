package com.doanchung.lab4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchung.app1.R;

public class Screen1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            Intent myIntent = new Intent(Screen1Activity.this, Screen2Activity.class);
            String productName = editText.getText().toString();
            myIntent.putExtra("productName", productName);

            myLauncher.launch(myIntent);
        });

    }
    // This is the new way to start an activity and get the result back
    //call register -> call Contracts -> call Callback
    private ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult myResult) {
                    if (myResult.getResultCode() == 10) {
                        Intent myIntent = myResult.getData();
                        String money = myIntent.getStringExtra("money");
                        String productName = myIntent.getStringExtra("productName");
                        TextView textView4 = findViewById(R.id.textView4);
                        textView4.setText(money);

                        TextView textView3 = findViewById(R.id.textView3);
                        textView3.setText("Sản phẩm"+" " + productName +" " +"có giá là: ");

                    }
                }
            }
    );
}