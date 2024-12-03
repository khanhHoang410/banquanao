package com.example.duan1;

import static android.content.ClipData.newIntent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.duan1.Dao.SanPhamDAO;
import com.example.duan1.Models.SanPham;

public class SuaSanPhamActivity extends AppCompatActivity {
    SanPham sanPham;
    EditText edtTenSanPham, edtGiaSanPham, edtSoLuongSanPham, edtMoTaSanPham;
    Button btnLuuSanPham;
    SanPhamDAO sanPhamDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_san_pham);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtTenSanPham = findViewById(R.id.etTenSanPham);
        edtGiaSanPham = findViewById(R.id.etGiaSanPham);
        edtSoLuongSanPham = findViewById(R.id.etSoLuongSanPham);
        edtMoTaSanPham = findViewById(R.id.etMoTaSanPham);
        btnLuuSanPham = findViewById(R.id.btnLuuSanPham);
        sanPhamDAO = new SanPhamDAO(this);
        SanPham sanPham = getIntent().getParcelableExtra("sanPham");

        if (sanPham != null) {
            // Hiển thị thông tin sản phẩm lên các EditText
            edtTenSanPham.setText(sanPham.getTenSanPham());
            edtGiaSanPham.setText(String.valueOf(sanPham.getGia()));
            edtSoLuongSanPham.setText(String.valueOf(sanPham.getSoLuong()));
            edtMoTaSanPham.setText(sanPham.getMoTa());
        }else {
            Toast.makeText(this, "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnLuuSanPham.setOnClickListener(v -> {
            // Lấy thông tin mới từ các EditText
            String tenSanPham = edtTenSanPham.getText().toString();
            String giaSanPhamStr = edtGiaSanPham.getText().toString();
            String soLuongSanPhamStr = edtSoLuongSanPham.getText().toString();
            String moTaSanPham = edtMoTaSanPham.getText().toString();

            if (tenSanPham.isEmpty()) {
                edtTenSanPham.setError("Tên sản phẩm không được để trống");
                return;
            }
            if (giaSanPhamStr.isEmpty() || !giaSanPhamStr.matches("\\d+(\\.\\d+)?")) {
                edtGiaSanPham.setError("Giá sản phẩm phải là số dương");
                return;
            }
            if (soLuongSanPhamStr.isEmpty() || !soLuongSanPhamStr.matches("\\d+")) {
                edtSoLuongSanPham.setError("Số lượng sản phẩm phải là số nguyên dương");
                return;
            }
            if (moTaSanPham.isEmpty()) {
                edtMoTaSanPham.setError("Mô tả sản phẩm không được để trống");
                return;
            }
            float giaSanPham = Float.parseFloat(giaSanPhamStr);
            int soLuongSanPham = Integer.parseInt(soLuongSanPhamStr);

            // Cập nhật thông tin sản phẩm
            sanPham.setTenSanPham(tenSanPham);
            sanPham.setGia(giaSanPham);
            sanPham.setSoLuong(soLuongSanPham);
            sanPham.setMoTa(moTaSanPham);


            // Cập nhật sản phẩm trong cơ sở dữ liệu
            if (sanPhamDAO.update(sanPham) > 0) {
                Toast.makeText(this, "Sửa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("sanpham_updated");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                sendBroadcast(intent);
                finish();
            } else {
                Toast.makeText(this, "Sửa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
            }

        });
    }
}