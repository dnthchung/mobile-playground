package com.doanchung.assignmentand101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //1
        TextInputEditText edtUser = findViewById(R.id.edtUser);
        TextInputLayout txtUser = findViewById(R.id.txtUser);

        TextInputEditText edtPassword = findViewById(R.id.edtPassword);
        TextInputLayout txtPassword = findViewById(R.id.txtPassword);

        TextInputEditText edtRePassword = findViewById(R.id.edtRePassword);
        TextInputLayout txtRePassword = findViewById(R.id.txtRePassword);

        Button btnBack = findViewById(R.id.btnBack);
        Button btnRegister = findViewById(R.id.btnRegister);

        //2
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String password = edtPassword.getText().toString();
                String rePassword = edtRePassword.getText().toString();

                //validate
                if (user.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
                    if (user.isEmpty()){
                        txtUser.setError("user is required!");
                    }else {
                        txtUser.setError(null);
                    }
                    if (password.isEmpty()){
                        txtPassword.setError("pass is required!");
                    }
                    if (rePassword.isEmpty()){
                        txtRePassword.setError("re pass is required");
                    }

                } else if (!password.equals(rePassword)){
                    Toast.makeText(RegisterActivity.this, "re password is not match!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("user", user);
                    intent.putExtra("password", password);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        edtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Đây là phương thức được gọi trước khi text bị thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Đây là phương thức được gọi ngay sau khi text bị thay đổi
                if (s.length() == 0){
                    txtUser.setError("user is required!");
                } else {
                    txtUser.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Đây là phương thức được gọi sau khi text bị thay đổi
            }
        });
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Đây là phương thức được gọi trước khi text bị thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Đây là phương thức được gọi ngay sau khi text bị thay đổi
                if (s.length() == 0){
                    txtPassword.setError("user is required!");
                } else {
                    txtPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Đây là phương thức được gọi sau khi text bị thay đổi
            }
        });
        edtRePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Đây là phương thức được gọi trước khi text bị thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Đây là phương thức được gọi ngay sau khi text bị thay đổi
                if (s.length() == 0){
                    txtRePassword.setError("user is required!");
                } else {
                    txtRePassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Đây là phương thức được gọi sau khi text bị thay đổi
            }
        });
    }
}