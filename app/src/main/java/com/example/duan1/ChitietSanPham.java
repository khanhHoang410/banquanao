package com.example.duan1;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.SanPhamRecyclerViewAdapter;
import com.example.duan1.Dao.GioHangDAO;
import com.example.duan1.Dao.SanPhamDAO;
import com.example.duan1.Database.CartData;
import com.example.duan1.Models.GioHang;
import com.example.duan1.Models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ChitietSanPham extends AppCompatActivity {
    ImageView imgChitietSanpham;
    TextView tvGia,tvMota, tvTenSanPham;
    Button btnAddtocart;
    GioHangDAO gioHangDAO;
    RecyclerView recycler_view_may_like;
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    SanPhamRecyclerViewAdapter sanphamAdapter;


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
        recycler_view_may_like = findViewById(R.id.recycler_view_may_like);
        tvGia=findViewById(R.id.tvGia);
        tvMota = findViewById(R.id.tvMota);
        tvTenSanPham =findViewById(R.id.tvTenSanpHam);
        sanPhamDAO = new SanPhamDAO(this);
        gioHangDAO = new GioHangDAO(this);
        recycler_view_may_like.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
       list= sanPhamDAO.getAll();
       GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
       sanphamAdapter = new SanPhamRecyclerViewAdapter(this, list);
       recycler_view_may_like.setAdapter(sanphamAdapter);
       recycler_view_may_like.setLayoutManager(layoutManager);
       sanphamAdapter.updateData(list);

        // Nhận dữ liệu từ intent
        int masanpham = getIntent().getIntExtra("maSanPham",-1);
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

        btnAddtocart = findViewById(R.id.btnaddto);
        btnAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // lấy thông tin sản phẩm
                String tensp = tvTenSanPham.getText().toString();
                float giasp = Float.parseFloat(tvGia.getText().toString().replace("$",""));
                String anh = imgChitietSanpham.getDrawable().toString();
                int masanpham = getIntent().getIntExtra("maSanPham",-1);

                // tạo một sản phẩm mới
                SanPham sanPham = new SanPham();
                sanPham.setTenSanPham(tensp);
                sanPham.setGia(giasp);
                sanPham.setAnh(anh);
                sanPham.setMaSanPham(masanpham);

                int userId = getIntent().getIntExtra("userId", -1);
                Log.d("mã người dùng", "userId: " + userId);
                GioHang gioHang = new GioHang();
                gioHang.setMaNguoiDung(userId);
                gioHang.setMaSanPham(masanpham);
                Log.d("mã sản phẩm", "masanpham: " + masanpham);
                gioHang.setTongTien(giasp);
                gioHang.setMaNguoiDung(userId);

                // thêm sản phẩm  vào array
//                List<SanPham> sanPhamList = new ArrayList<>();
//                sanPhamList.add(sanPham);
//                CartData.cartItems.add(sanPham);
//                // thêm vào giỏ hàng
                long result = gioHangDAO.insert(gioHang);

                if (result>0){
                    Toast.makeText(ChitietSanPham.this, "đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    Log.d("CartActivity", "CartData.cartItems size: " + CartData.cartItems.size());
                    Intent intent = new Intent(ChitietSanPham.this, CartActivity.class);
                    intent.putExtra("gioHang", gioHang);
                    intent.putExtra("userId", userId);

                    // Truyền userId
                    startActivity(intent);
                }else {
                    Toast.makeText(ChitietSanPham.this, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                }
                // truyền array sang cart actitity

//                intent.putExtra("gioHang", gioHang);
//                intent.putExtra("sanPhamList", (ArrayList<SanPham>) sanPhamList); // Không cần thiết// Khởi chạy CartActivity mà không chuyển màn hình
//

            }
        });




        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            //Lấy GioHang từ Intent
            GioHang gioHang = data.getParcelableExtra("gioHang");

            // Cập nhật giỏ hàng trong ChitietSanPham
            if (gioHang != null) {
                SanPham sanPham = sanPhamDAO.getID(gioHang.getMaSanPham());

                if (sanPham != null) {
                    CartData.cartItems.add(sanPham);
                }
                gioHangDAO.insert(gioHang); // Thêm vào cơ sở dữ liệu}
            }
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

