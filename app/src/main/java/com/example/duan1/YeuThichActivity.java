package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.SanPhamRecyclerViewAdapter;
import com.example.duan1.Models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class YeuThichActivity extends AppCompatActivity {
    ImageView imgReturn;
    List<SanPham> yeuthichList = new ArrayList<>();
    RecyclerView rclYeuthich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_yeu_thich);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgReturn = findViewById(R.id.imgReturn);

        yeuthichList = getIntent().getParcelableArrayListExtra("yeuthichList");

        if (yeuthichList == null) {
            yeuthichList = new ArrayList<>();
        }
        if (yeuthichList != null) {
            Log.d("YeuThichList", "Danh sách yêu thích đã nhận: " + yeuthichList.size());
            for (SanPham sp : yeuthichList) {
                Log.d("YeuThichList", "Tên sản phẩm: " + sp.getTenSanPham());
            }
        }

        rclYeuthich = findViewById(R.id.rclYeuthich);
        SanPhamRecyclerViewAdapter sanPhamAdapter = new SanPhamRecyclerViewAdapter(this, yeuthichList,null,-1);
        sanPhamAdapter.updateData(yeuthichList);
        rclYeuthich.setLayoutManager(new GridLayoutManager(this, 2)); // Số 2 là số cột
        rclYeuthich.setAdapter(sanPhamAdapter);

        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putParcelableArrayListExtra("yeuthichList", new ArrayList<>(yeuthichList));
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}