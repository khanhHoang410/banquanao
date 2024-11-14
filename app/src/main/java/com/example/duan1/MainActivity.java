package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment fragment;
    NavigationView nav_view;
    private DrawerLayout drawer;
    ImageView imgYeuThich, imgGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgYeuThich = findViewById(R.id.imgYeuThich);
        imgYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,YeuThichActivity.class));
            }
        });
        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });























        ////// phần chuyển màn hình
        drawer = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bnv);
        fragment = new BlankFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.toDay) {
                    fragment = new BlankFragment();
                } else if (menuItem.getItemId() == R.id.calender) {
                    fragment = new BlankFragment2();
                } else if (menuItem.getItemId() == R.id.health) {
                    fragment = new BlankFragment3();
                } else if (menuItem.getItemId() == R.id.social) {
                    fragment = new BlankFragment4();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                return true;
            }
        });
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Đóng drawer
                drawer.closeDrawer(GravityCompat.START);

                // Chuyển đổi fragment dựa trên mục được chọn
                if (menuItem.getItemId() == R.id.nav_tshirt) {
                    fragment = new BlankFragment();
                } else if (menuItem.getItemId() == R.id.nav_quan) {
                    fragment = new BlankFragment2();
                } else if (menuItem.getItemId() == R.id.nav_aokhoac) {
                    fragment = new BlankFragment3();
                } else if (menuItem.getItemId() == R.id.nav_ThongKe) {
                    fragment = new BlankFragment4();
                }

                // Thực hiện giao dịch fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                return true;
            }
        });
        ///////////////////
    }
}