package com.doanchung.assignmentand102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.doanchung.assignmentand102.fragments.AboutFragment;
import com.doanchung.assignmentand102.fragments.ProductFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. anh xa from main_activity.xml
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);

        //2. show fragment product khi chạy app
        /**
         * 1. setSupportActionBar(toolbar) -> dùng để thay thế ActionBar mặc định của Activity
         * 2. getSupportActionBar().setDisplayHomeAsUpEnabled(true) -> hiển thị nút back trên toolbar
         * 3. getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu) -> thay đổi icon cho nút back thành icon menu
         */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        //set default fragment = Product fragment
        ProductFragment productFragmentDefault = new ProductFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bodyLayout, productFragmentDefault)
                .commit();

        //click vào item trên menu -> fragment tương ứng sẽ được hiển thị
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if(item.getItemId() == R.id.mQLSP){
                    fragment = new ProductFragment();

                } else if (item.getItemId() == R.id.mGioiThieu){
                    fragment = new AboutFragment();

                }else {
                    //mặc định sẽ hiển thị fragment product -> tùy
                    fragment = new ProductFragment();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.bodyLayout, fragment)
                        .commit();
                //đóng drawer khi chọn xong item
                drawerLayout.closeDrawer(GravityCompat.START);
                //set title cho toolbar khi chọn item, thay đổi dựa trên title của item
                getSupportActionBar().setTitle(item.getTitle());
                return false;
            }
        });

    }

    //Hàm này sẽ thực hiện việc hiển thị menu khi click vào icon menu trên toolbar
    //Note: Nếu không override hàm này thì khi click vào icon menu trên toolbar sẽ không hiển thị menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId() == android.R.id.home){
        /**
         * drawerLayout.openDrawer(GravityCompat.START) -> Mở drawer khi click vào icon menu (mmở sidebar khi click vào icon menu trên toolbar)
         * drawerLayout.closeDrawer(GravityCompat.START) -> Đóng drawer khi click vào icon back (đóng sidebar khi click vào icon back trên toolbar)
         */
        drawerLayout.openDrawer(GravityCompat.START);
    }
        return super.onOptionsItemSelected(item);
    }
}