package com.chungdoan.mob2041.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


import com.chungdoan.mob2041.R;
import com.chungdoan.mob2041.fragments.CartFragment;
import com.chungdoan.mob2041.fragments.ExploreFragment;
import com.chungdoan.mob2041.fragments.WishListFragment;
import com.google.android.material.navigation.NavigationView;

public class DemoSideBarActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    NavigationView navigationView;

    //open = avatar
    ImageView imageViewAvatar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_side_bar);

        //mapping
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
//        imageViewAvatar = findViewById(R.id.imageViewAvatar); => đặt thằng

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Đổi icon mặc định thành icon menu
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //set fragment mặc định khi mở app
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bodyLayout, new ExploreFragment())
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                final Fragment fragment;

                // Xác định fragment cần chuyển đến
                if (menuItem.getItemId() == R.id.nav_favorite) {
                    fragment = new WishListFragment();
                } else if (menuItem.getItemId() == R.id.nav_home) {
                    //start activity
                    startActivity(new Intent(DemoSideBarActivity.this, HomeShopActivity.class));
                    fragment = null;
                } else {
                    fragment = null;
                }


                // Cập nhật tiêu đề của toolbar
                //If a fragment was selected (not an activity)
                if (fragment != null) {
                    getSupportActionBar().setTitle(menuItem.getTitle());

                    // Đóng ngăn kéo trước khi chuyển fragment
                    mDrawerLayout.closeDrawer(GravityCompat.START);

                    // Sử dụng listener để chờ khi ngăn kéo hoàn toàn đóng xong mới thay đổi fragment
                    mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
                        @Override
                        public void onDrawerSlide(View drawerView, float slideOffset) {
                        }

                        @Override
                        public void onDrawerOpened(View drawerView) {
                        }

                        @Override
                        public void onDrawerClosed(View drawerView) {
                            // Thay đổi fragment khi ngăn kéo đã đóng hoàn toàn
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.bodyLayout, fragment)
                                    .commit();

                            // Gỡ bỏ listener sau khi hoàn thành
                            mDrawerLayout.removeDrawerListener(this);
                        }

                        @Override
                        public void onDrawerStateChanged(int newState) {
                        }
                    });
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(navigationView);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}