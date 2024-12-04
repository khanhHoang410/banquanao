package com.example.duan1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.DonHangAdapter;
import com.example.duan1.Dao.DonHangDAO;
import com.example.duan1.Models.DonHang;

import java.util.List;

public class DonhangActivity extends AppCompatActivity {
    RecyclerView rvDonHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donhang);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rvDonHang = findViewById(R.id.rvDonHang);
        rvDonHang.setLayoutManager(new LinearLayoutManager(this));

        DonHangDAO donHangDAO = new DonHangDAO(this);
        List<DonHang> donHangList = donHangDAO.getAll();

        DonHangAdapter donHangAdapter = new DonHangAdapter(this, donHangList);
        rvDonHang.setAdapter(donHangAdapter);

    }
}