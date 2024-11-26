package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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

import com.example.duan1.Adapter.SanPhamRecyclerViewAdapter;
import com.example.duan1.Models.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements BlankFragment.OnYeuThichChangeListener{
    BottomNavigationView bottomNavigationView;
    Fragment fragment;
    NavigationView nav_view;
    private DrawerLayout drawer;
    ImageView imgYeuThich, imgGioHang;
    List<SanPham> yeuthichList = new ArrayList<>();
    SanPhamRecyclerViewAdapter adapter;

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
        imgGioHang = findViewById(R.id.imgGioHang);
        adapter = new SanPhamRecyclerViewAdapter(this, yeuthichList, sanPham -> {
            // Xử lý sự kiện yêu thích trong adapter (nếu cần)
            // Ví dụ: cập nhật danh sách yêu thích trong MainActivity
            if (sanPham.getYeuThich()) {
                if (!yeuthichList.contains(sanPham)) {
                    yeuthichList.add(sanPham);
                }
            } else {
                yeuthichList.remove(sanPham);
            }
        });
        imgYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Kích thước danh sách yêuthích: " + yeuthichList.size());
                Intent intent = new Intent(MainActivity.this, YeuThichActivity.class);
                intent.putParcelableArrayListExtra("yeuthichList", (ArrayList<? extends Parcelable>) yeuthichList);
                startActivityForResult(intent, 1);
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
        ///////////////////
    }
    @Override
    public void onYeuThichChange(List<SanPham> yeuthichList) {
        this.yeuthichList = yeuthichList;
        adapter.notifyDataSetChanged(); // Thông báo cho adapter về sự thay đổi
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1&& resultCode == RESULT_OK && data != null) {
            yeuthichList = data.getParcelableArrayListExtra("yeuthichList");
            if (yeuthichList == null) {
                yeuthichList = new ArrayList<>();
            }
            // Cập nhật adapter của RecyclerView
            adapter.updateData(yeuthichList); // Cập nhật adapter với danh sách mới
        }
    }
}