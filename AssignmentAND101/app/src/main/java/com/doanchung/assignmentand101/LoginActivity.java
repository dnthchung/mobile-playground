package com.doanchung.assignmentand101;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    /**
     * 2 biến này dùng để save data từ RegisterActivity,
     * sau đó được dùng để so sánh với data người dùng nhập vào để login
     */
    String userRegister = "";
    String passwordRegister = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //1
        TextInputEditText edtUser = findViewById(R.id.edtUser);
        TextInputLayout txtUser = findViewById(R.id.txtUser);

        TextInputEditText edtPassword = findViewById(R.id.edtPassword);
        TextInputLayout txtPassword = findViewById(R.id.txtPassword);

        CheckBox chkRemember = findViewById(R.id.chkRemember);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        //2
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                myLauncher.launch(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLogin = edtUser.getText().toString();
                String passwordLogin = edtPassword.getText().toString();

                if (userLogin.length() > 0 && passwordLogin.length() > 0 && userLogin.equals(userRegister) && passwordLogin.equals(passwordRegister)) {
                    //remember me
                    if(chkRemember.isChecked()){
                        //1. call file need to store (name of file reference, mode)
                        //2. edit to file that be called above
                        //3. store data to file by (key, value)
                        //4. save (apply)
                        SharedPreferences sharedPreferencesNe = getSharedPreferences("info data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferencesNe.edit();
                        editor.putString("userLogin", userLogin);
                        editor.putString("passwordLogin", passwordLogin);
                        editor.putBoolean("chkRememberNe", chkRemember.isChecked());
                        editor.apply();
                    }
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //clear previous activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //3
        /**
         *  kiểm tra thông tin đăng nhập người dùng có chọn lưu lại hay không
         *  1. get data out(file name, default value - nếu như k tìm thấy thì đặt giá trị mặc định là false)
         *  2. check biến remember nếu như có thì lấy ra user và password
         *  3. set data to view(user, password input field)
         **/

        SharedPreferences sharedPreferences = getSharedPreferences("info data", MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("chkRememberNe", false);
        if(isRemember){
            String user = sharedPreferences.getString("userLogin", "");
            String password = sharedPreferences.getString("passwordLogin", "");

            //set data to view
            edtUser.setText(user);
            edtPassword.setText(password);
            chkRemember.setChecked(isRemember);

            //set data to variable register
            userRegister = user;
            passwordRegister = password;
        }else {
            //nếu như k chọn remember thì clear data đã lưu
            SharedPreferences.Editor editor = getSharedPreferences("info data", MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
        }


    }

    //activity result to get data from RegisterActivity
    private ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        Intent intent = o.getData();

                        userRegister = intent.getStringExtra("user");
                        passwordRegister = intent.getStringExtra("password");

                        Toast.makeText(LoginActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();

                        TextInputEditText edtUser = findViewById(R.id.edtUser);
                        TextInputEditText edtPassword = findViewById(R.id.edtPassword);

                        edtUser.setText(userRegister);
                        edtPassword.setText(passwordRegister);
                    }
                }
            }
    );
}