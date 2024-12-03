package com.example.duan1;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.CartAdapter;
import com.example.duan1.Dao.GioHangDAO;
import com.example.duan1.Database.CartData;
import com.example.duan1.Models.GioHang;
import com.example.duan1.Models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ImageView img_return;
    RecyclerView rclcart;
    CartAdapter adapter;
    TextView totalPriceTextView;
    GioHangDAO gioHangDAO;
    GioHang gioHang;
    List<GioHang> gioHangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gioHangDAO = new GioHangDAO(this);
        img_return = findViewById(R.id.img_return);
        rclcart = findViewById(R.id.recycler_view);
        totalPriceTextView = findViewById(R.id.total_price);
        rclcart.setLayoutManager(new LinearLayoutManager(this));

        int userId = getIntent().getIntExtra("userId", -1);
        Log.d("CartActivity", "userId: " + userId);
        // nhận dữ liệu từ intent
        gioHangList = gioHangDAO.getAll(userId);
        Log.d("CartActivity", "Gio hang list: " + gioHangList.size());
        if (gioHangList.isEmpty()) {
            Toast.makeText(this, "Không có sản phẩm nào trong giỏ hàng", Toast.LENGTH_SHORT).show();
//            ArrayList<SanPham> sanPhamList = (ArrayList<SanPham>) CartData.cartItems;
//            Log.d("CartActivity", "Received sanPhamList size: " + sanPhamList.size());
//            if (sanPhamList!=null && !sanPhamList.isEmpty()){
//                for (SanPham sanPham : sanPhamList){
//                    GioHang gioHang = new GioHang();
//                    gioHang.setMaDonHang(1);
//                    gioHang.setMaSanPham(sanPham.getMaSanPham());
//                    gioHang.setTongTien(sanPham.getGia());
//                    gioHangList.add(gioHang);
//                    gioHangDAO.insert(gioHang);
//                }
        } else {
            adapter = new CartAdapter(this, gioHangList, totalPriceTextView, userId);
            adapter.updateTotalPrice();
            rclcart.setAdapter(adapter);

            img_return.setOnClickListener(v -> finish());


        }
//    public void updateCart(GioHang gioHang) {
//        gioHangList.add(gioHang);
//        adapter.notifyDataSetChanged();
//        adapter.updateTotalPrice(); // Cập nhật tổng tiền
//    }


//        adapter  = new CartAdapter(this,gioHangList,totalPriceTextView,userId);
//        adapter.updateTotalPrice();
//        rclcart.setAdapter(adapter);


        img_return.setOnClickListener(v -> finish());


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cập nhật lại gioHangList khi Activity được hiển thị lại
        int userId = getIntent().getIntExtra("userId", -1);
        Log.d("CartActivity", "userId: " + userId);
        gioHangList = gioHangDAO.getAll(userId);
        if (adapter != null) {
            adapter.updateData(gioHangList); // Cập nhật dữ liệu cho adapter
            adapter.notifyDataSetChanged(); // Thông báo cho adapter cập nhật giao diện
            adapter.updateTotalPrice(); // Cập nhật tổng tiền
        }

    }


}

//}

