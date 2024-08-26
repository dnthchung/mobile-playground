package com.chungdoan.mob2041.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chungdoan.mob2041.R;
import com.chungdoan.mob2041.daos.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {

    private NguoiDungDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize NguoiDungDAO inside onCreate
        userDAO = new NguoiDungDAO(this);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvSignUp = findViewById(R.id.tvSignUp);
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            } else {
                // Call DAO to check login
                boolean checkLogin = userDAO.checkLogin(username, password);
                if (checkLogin) {
                    Toast.makeText(this, "Login successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
