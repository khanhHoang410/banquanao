package com.example.duan1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Dao.NguoiDungDAO;
import com.example.duan1.Models.NguoiDung;

import java.util.ArrayList;

public class Shipped extends AppCompatActivity {
private NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shipped);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView nameInput = findViewById(R.id.tvName);
        TextView phoneInput = findViewById(R.id.tvSdt);
        TextView addressInput = findViewById(R.id.tvDiaChi);
        nguoiDungDAO = new NguoiDungDAO(this);
        TextView tvEmpty = findViewById(R.id.tvEmpty);
        ListView listView = findViewById(R.id.lvGiaoHang);
        ArrayList list = new ArrayList();
        // Kiểm tra xem danh sách có rỗng không
        if (list.isEmpty()) {
            listView.setVisibility(View.GONE); // Ẩn ListView
            tvEmpty.setVisibility(View.VISIBLE); // Hiển thị thông báo
        } else {
            listView.setVisibility(View.VISIBLE); // Hiển thị ListView
            tvEmpty.setVisibility(View.GONE); // Ẩn thông báo
        }
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        NguoiDung user = nguoiDungDAO.getUserByEmail(email);
        if (user != null) {
            nameInput.setText("Tên người dùng: "+user.getTenNguoiDung());
            phoneInput.setText("Số điện thoại: "+"+84"+String.valueOf(user.getSdt()));
            addressInput.setText("Địa chỉ: "+user.getDiaChi());
        }
        findViewById(R.id.btnReturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}