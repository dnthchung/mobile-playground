package com.doanchung.assignmentand101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        //Tổng thời gian count down(mili giây 1s = 1000 mili s, thời gian đếm ngược mỗi lần là 1 giây
        //nhớ .start()
        new CountDownTimer(3000,1000) {
            //Hàm này sẽ được gọi sau mỗi lần đếm ngược
            public void onTick(long millisUntilFinished) {
            }
            //Hàm này sẽ được gọi sau khi đếm ngược kết thúc
            public void onFinish() {
                //Chuyển màn hình từ WelcomeActivity sang LoginActivity
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}