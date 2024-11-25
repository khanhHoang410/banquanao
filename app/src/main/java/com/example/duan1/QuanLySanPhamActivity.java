package com.example.duan1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.SanPhamRecyclerViewAdapter;
import com.example.duan1.Dao.SanPhamDAO;
import com.example.duan1.Models.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class QuanLySanPhamActivity extends AppCompatActivity {
    RecyclerView rcl;
    SanPhamRecyclerViewAdapter adapter;
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    FloatingActionButton fltthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quan_ly_san_pham);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcl = findViewById(R.id.rclqlsanpham);
        sanPhamDAO = new SanPhamDAO(this);
        list = sanPhamDAO.getAll();
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rcl.setLayoutManager(layoutManager);
        adapter = new SanPhamRecyclerViewAdapter(this, list);
        rcl.setAdapter(adapter);
        adapter.updateData(list);
        fltthem = findViewById(R.id.fltthem);
        fltthem.setOnClickListener(v -> {
            startActivity(new Intent(this, ThemSanPhamActivity.class));
        });
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                list= sanPhamDAO.getAll();
                adapter.updateData(list);
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter("sanpham_added"));
        adapter.setOnItemLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int position = rcl.getChildAdapterPosition(v);
                if (position != RecyclerView.NO_POSITION) {
                    new AlertDialog.Builder(QuanLySanPhamActivity.this)
                            .setTitle("Xác nhận xóa")
                            .setMessage("Bạn có muốn xóa sản phẩm này không?")
                            .setPositiveButton("Có", (dialog, which) -> {
                                SanPham sanPham = list.get(position);
                                sanPhamDAO.delete(sanPham.getMaSanPham());
                                list.remove(position);
                                adapter.notifyItemRemoved(position);
                                adapter.notifyItemRangeChanged(position, list.size());
                            })
                            .setNegativeButton("Không", null)
                            .show();
                    return true;
                }
                    return false;

            }
        });

    }

}