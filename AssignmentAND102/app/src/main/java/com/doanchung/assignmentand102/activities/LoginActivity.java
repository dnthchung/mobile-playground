package com.doanchung.assignmentand102.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doanchung.assignmentand102.MainActivity;
import com.doanchung.assignmentand102.R;
import com.doanchung.assignmentand102.dao.UserDAO;
import com.doanchung.assignmentand102.utils.SendMail;

public class LoginActivity extends AppCompatActivity {

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ánh xạ
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvSignUp = findViewById(R.id.tvSignUp);
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);

        userDAO = new UserDAO(this);

        //1. login function
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy dữ liệu từ edittext
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                //Đưa data qua dao
                boolean checkLogin = userDAO.checkLogin(username, password);
                if(checkLogin) {
                    Toast.makeText(LoginActivity.this, "Login successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        //2. sign up function
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        //3. forgot password function
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogForgotPassword();
            }
        });

    }

    private  void showDialogForgotPassword() {
        /**
         * - context: là activity hiện tại
         * - LayoutInflater: là đối tượng dùng để tạo view từ file xml
         * - View: là đối tượng view nhận layout thông qua LayoutInflater
         * - QUAN TRỌNG -> Khi ánh xạ các view trong layout thì phải thông qua view
         * - Send mail : add 3 thư viện jar vào folder libs với định dạng Project xong bôi đen các lib và chọn "save as library"
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_ad_forgot_password, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Dùng để hủy thao tác người dùng click ra bên ngoài dialog -> làm mất dialog
        alertDialog.setCancelable(false);

        //ánh xạ thông qua view
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnSendEmail = view.findViewById(R.id.btnSendEmail);
        EditText edtEmail = view.findViewById(R.id.edtEmail);

        //cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        //send email
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = userDAO.forgotPassword(email);
                if(password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Email không tồn tại trong hệ thống.", Toast.LENGTH_SHORT).show();
                }else {
                    SendMail sendMail = new SendMail();
                    sendMail.Send(LoginActivity.this, email, "Forgot password", "Your password is: " + password + "\n");
                    Toast.makeText(LoginActivity.this, "Your password is: " + password, Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });



    }
}