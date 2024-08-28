package com.chungdoan.mob2041.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chungdoan.mob2041.R;
import com.google.android.material.bottomappbar.BottomAppBar;

public class MainActivity extends AppCompatActivity {

    /**
     * - Phân quyền bằng cách - Shared Preferences
     * - ở trong function login của DAO, gọi thằng shared preferences để lưu role của user vào trong máy.
     * - sau đó ở activity này thì xử lý, call shared preferences để lấy ra role của user đã save - nhớ là login thành công mới được save role.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapping
        LinearLayout layoutManageCategory = findViewById(R.id.layoutManageCategory);
        LinearLayout layoutManageBooks = findViewById(R.id.layoutManageBooks);
//        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);

        LinearLayout layoutSideBar = findViewById(R.id.layoutSideBar);


        //get role save in reference
        SharedPreferences sharedPreferences = getSharedPreferences("DATA_USER_MOB2041", MODE_PRIVATE);
        int role = sharedPreferences.getInt("role", 0);
        /**
         * role : 1- user \ 2 - thủ thư \ 3 - admin
         * - visibility : GONE (không hiển thị - mất hoàn toàn) - VISIBLE (hiển thị) - INVISIBLE (ẩn - vẫn giữ khoảng trống)
         */
        switch (role){
            case 1:
                //user
//                layoutManageCategory.setEnabled(false);
//                layoutManageBooks.setEnabled(false);
                layoutManageBooks.setVisibility(View.GONE);
                layoutManageCategory.setVisibility(View.VISIBLE);
                break;
            case 2:
                //thủ thư
//                layoutManageCategory.setEnabled(true);
//                layoutManageBooks.setEnabled(true);
                layoutManageBooks.setVisibility(View.VISIBLE);
                layoutManageCategory.setVisibility(View.GONE);
                break;
            case 3:
                //admin
//                layoutManageCategory.setEnabled(true);
//                layoutManageBooks.setEnabled(true);
                layoutManageBooks.setVisibility(View.GONE);
                layoutManageCategory.setVisibility(View.GONE);
                break;
            default:
                //default
                layoutManageCategory.setEnabled(false);
                layoutManageBooks.setEnabled(false);
                break;
        }


        layoutManageCategory.setOnClickListener(v -> {
            //go to manage category
            startActivity(
                    new Intent(MainActivity.this, CategoryActivity.class)
            );
        });

        layoutManageBooks.setOnClickListener(v -> {
            //go to manage books
            startActivity(
                    new Intent(MainActivity.this, BookActivity.class)
            );
        });

        //1. demo side bar / navigation drawer
        layoutSideBar.setOnClickListener( v -> {
                startActivity(
                        new Intent(
                                MainActivity.this, DemoSideBarActivity.class
                        )
                );
        });




    }
}