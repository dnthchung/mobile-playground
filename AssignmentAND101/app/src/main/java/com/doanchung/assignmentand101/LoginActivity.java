package com.doanchung.assignmentand101;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText edtUser = findViewById(R.id.edtUser);
        TextInputLayout txtUser = findViewById(R.id.txtUser);

        TextInputEditText edtPassword = findViewById(R.id.edtPassword);
        TextInputLayout txtPassword = findViewById(R.id.txtPassword);

        CheckBox chkRemember = findViewById(R.id.chkRemember);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                myLauncher.launch(intent);
            }
        });

    }

    private ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        Intent intent = o.getData();

                        String user = intent.getStringExtra("user");
                        String password = intent.getStringExtra("password");
                        Toast.makeText(LoginActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        TextInputEditText edtUser = findViewById(R.id.edtUser);
                        TextInputEditText edtPassword = findViewById(R.id.edtPassword);

                        edtUser.setText(user);
                        edtPassword.setText(password);
                    }
                }
            }
    );
}