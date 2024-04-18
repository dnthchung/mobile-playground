package com.doanchung.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnOpenWeb = findViewById(R.id.btnOpenWeb);
        Button btnCall = findViewById(R.id.btnCall);
        Button btnSMS = findViewById(R.id.btnSMS);


        EditText edtUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);

        //open web
        btnOpenWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW);
                myIntent.setData(Uri.parse("https://www.google.com"));
                startActivity(myIntent);
            }
        });
        //call : DIAL -> dialing
        btnCall.setOnClickListener(v -> {
            Intent myIntent = new Intent(Intent.ACTION_DIAL);
            myIntent.setData(Uri.parse("tel:0949602355"));
            startActivity(myIntent);
        });
        //sms
        btnSMS.setOnClickListener(v -> {
            Intent myIntent = new Intent(Intent.ACTION_SENDTO);
            myIntent.setData(Uri.parse("smsto:0949602355"));
            myIntent.putExtra("sms_body", "Hello");
            startActivity(myIntent);
        });

        //login
        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = etPassword.getText().toString();

            //transfer(trc khi start data to another activity / change screen - 2 giá trị (người gửi .this, người nhận .class)
            Intent myIntent = new Intent(LoginActivity.this, HomeActivity.class);
            //entry point : /transfer data
//            myIntent.putExtra("username", username);
//            myIntent.putExtra("password", password);
//            myIntent.putExtra("old", 1);

            //Using bundle
            Bundle mybundle = new Bundle();
            mybundle.putString("username", username);
            mybundle.putString("password", password);
            mybundle.putInt("old", 1);
            //send bundle
            myIntent.putExtra("myBundle", mybundle);
            startActivity(myIntent);
        });

        //register
        btnRegister.setOnClickListener(v -> {
            Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(myIntent);
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("liferCycle ---> ", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("liferCycle ---> ", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("liferCycle ---> ", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("liferCycle ---> ", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("liferCycle ---> ", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("liferCycle ---> ", "onRestart");
    }
}