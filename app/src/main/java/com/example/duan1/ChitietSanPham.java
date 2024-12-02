package com.example.duan1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Adapter.DanhGiaAdapter;
import com.example.duan1.Dao.DanhGiaDAO;
import com.example.duan1.Models.DanhGia;

import java.util.ArrayList;

public class ChitietSanPham extends AppCompatActivity {
    ImageView imgChitietSanpham;
    TextView tvGia,tvMota, tvTenSanPham;
    DanhGiaDAO danhGiaDAO;
    EditText edDanhGia;
    ImageView btnDanhGia;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.chitietsanpham);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listDanhGia = findViewById(R.id.listDanhGia);
        imgChitietSanpham=findViewById(R.id.imgChiTietanh);
        tvGia=findViewById(R.id.tvGia);
        tvMota = findViewById(R.id.tvMota);
        tvTenSanPham =findViewById(R.id.tvTenSanpHam);
        danhGiaDAO = new DanhGiaDAO(this);
        edDanhGia = findViewById(R.id.edDanhGia);
        btnDanhGia = findViewById(R.id.btnDanhGia);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int maNguoiDung = sharedPreferences.getInt("maNguoiDung", -1);
        int maSanpham = getIntent().getIntExtra("maSanPham",-1);
        ArrayList<DanhGia> list = danhGiaDAO.getAll(maSanpham);
        DanhGiaAdapter adapter = new DanhGiaAdapter(this,list);
        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String danhgia = edDanhGia.getText().toString();
                danhGiaDAO.insertDanhGia(maSanpham,maNguoiDung,danhgia);
                edDanhGia.setText("");
                adapter.notifyDataSetChanged();
            }
        });




        listDanhGia.setAdapter(adapter);











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