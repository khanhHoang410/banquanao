package com.example.duan1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Dao.DonHangDAO;
import com.example.duan1.Dao.GioHangDAO;
import com.example.duan1.Models.DonHang;

public class Xacnhanthongtindiachi extends AppCompatActivity {
    TextView tv_total_value,tv_address_value;
    TextView tv_return_to_customer_info;
    Button btn_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xacnhanthongtindiachi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv_total_value = findViewById(R.id.tv_total_value);
        tv_address_value = findViewById(R.id.tv_address_value);
        btn_continue = findViewById(R.id.btn_continue);
        int maDonHang = getIntent().getIntExtra("maDonHang", -1);


        DonHangDAO donHangDAO =new DonHangDAO(this);
        DonHang donHang = donHangDAO.getById(maDonHang);
        int maNguoidung = donHang.getMaNguoiDung();
        String diaChi = donHang.getDiaChi();
        float tongTien = donHang.getTongTien();
        tv_total_value.setText("$"+tongTien);
        tv_address_value.setText(diaChi);

        tv_return_to_customer_info = findViewById(R.id.tv_return_to_customer_info);
        tv_return_to_customer_info.setOnClickListener(view -> {
            finish();
        });
        btn_continue.setOnClickListener(v -> {
            Toast.makeText(Xacnhanthongtindiachi.this, "Đơn hàng của bạn đã được xử lý", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Xacnhanthongtindiachi.this, DonhangActivity.class);
//            intent.putExtra("maDonHang", maDonHang);
//            startActivity(intent);
            GioHangDAO gioHangDAO = new GioHangDAO(this);
            int deletedRows = gioHangDAO.deleteByUserId(maNguoidung);
            if (deletedRows > 0) {
                Toast.makeText(this, "Đã xóa giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Lỗi khi xóa giỏ hàng", Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(Xacnhanthongtindiachi.this,MainActivity.class));
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("maNguoidung", maNguoidung);
            editor.apply();
        });
    }
}