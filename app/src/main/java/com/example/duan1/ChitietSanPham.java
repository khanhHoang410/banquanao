package com.example.duan1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChitietSanPham extends AppCompatActivity {
    ImageView imgChitietSanpham;
    TextView tvGia,tvMota, tvTenSanPham;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.chitietsanpham);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgChitietSanpham=findViewById(R.id.imgChiTietanh);
        tvGia=findViewById(R.id.tvGia);
        tvMota = findViewById(R.id.tvMota);
        tvTenSanPham =findViewById(R.id.tvTenSanpHam);

        // Nhận dữ liệu từ intent

        String tenSanpham = getIntent().getStringExtra("tenSanPham");
        String gia = getIntent().getStringExtra("gia");
        String mota = getIntent().getStringExtra("moTa");
        String anhsanpham = getIntent().getStringExtra("anh");
        tvTenSanPham.setText(tenSanpham);
        tvGia.setText(gia);
        tvMota.setText(mota);
        // hiển thị ảnh lên sản phẩm
        Bitmap bitmap = getBitmapFromDrawable(anhsanpham,this);
        if (bitmap != null) {
            imgChitietSanpham.setImageBitmap(bitmap);
        }

        }
    private Bitmap getBitmapFromDrawable(String fileName, Context context) {
        // Lấy resource ID từ tên file
        int resourceId = context.getResources().getIdentifier(fileName.replace(".webp", ""), "drawable", context.getPackageName());
        if (resourceId != 0) {
            // Chuyển đổi resource ID thành Bitmap
            return BitmapFactory.decodeResource(context.getResources(), resourceId);
        }
        return null; // Trả về null nếu không tìm thấy ảnh
    }
}

