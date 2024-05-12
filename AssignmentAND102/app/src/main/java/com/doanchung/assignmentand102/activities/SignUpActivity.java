package com.doanchung.assignmentand102.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doanchung.assignmentand102.R;
import com.doanchung.assignmentand102.dao.UserDAO;

public class SignUpActivity extends AppCompatActivity {

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //ánh xạ
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtRePassword = findViewById(R.id.edtRePassword);
        EditText edtFullName = findViewById(R.id.edtFullName);
        EditText edtEmail = findViewById(R.id.edtEmail);

        Button btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy dữ liệu từ edittext
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String rePassword = edtRePassword.getText().toString();
                String fullName = edtFullName.getText().toString();
                String email = edtEmail.getText().toString();

                userDAO = new UserDAO(SignUpActivity.this);

                //check password
                if(password.equals(rePassword)) {
                    //đưa data qua dao
                    boolean checkSignUp = userDAO.signUp(username, password, fullName, email);
                    //nếu đúng thì insert vào db
                    if(checkSignUp) {
                        Toast.makeText(SignUpActivity.this, "Create Account Successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(SignUpActivity.this, "Create new Account fail!!", Toast.LENGTH_SHORT).show();
                    }
                    //nếu sai thì thông báo
                }else {
                    //thông báo
                    Toast.makeText(SignUpActivity.this, "Password and Re-Password not match.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}