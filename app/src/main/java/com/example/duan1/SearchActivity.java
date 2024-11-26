package com.example.duan1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.SanPhamRecyclerViewAdapter;
import com.example.duan1.Dao.SanPhamDAO;
import com.example.duan1.Models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText searchEditText;
    RecyclerView searchResultsRecyclerView;
    SanPhamRecyclerViewAdapter sanPhamAdapter;
    private SanPhamDAO sanPhamDAO;
    List<SanPham> searchResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchEditText = findViewById(R.id.searchEditText);
        searchResultsRecyclerView = findViewById(R.id.searchResultsRecyclerView);

        sanPhamDAO = new SanPhamDAO(this);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        searchResults = new ArrayList<>();
        sanPhamAdapter = new SanPhamRecyclerViewAdapter(this, searchResults, new SanPhamRecyclerViewAdapter.OnYeuThichChangeListener() {
            @Override
            public void onYeuThichChange(SanPham sanPham) {
                // Xử lý thay đổi yêu thích của sản phẩm tại đây
                Toast.makeText(SearchActivity.this, "Sản phẩm yêu thích đã được cập nhật!", Toast.LENGTH_SHORT).show();
            }
        });
        searchResultsRecyclerView.setAdapter(sanPhamAdapter);


        // Lắng nghe thay đổi trong trường tìm kiếm
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Gọi phương thức tìm kiếm khi người dùng nhập vào từ khóa
                performSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void performSearch(String query) {
        // Loại bỏ dấu khỏi từ khóa tìm kiếm
        String normalizedQuery = StringUtils.removeAccent(query).toLowerCase();

        // Lấy danh sách tất cả sản phẩm
        List<SanPham> allProducts = sanPhamDAO.getAll();

        // Lọc kết quả theo từ khóa
        searchResults.clear();
        for (SanPham product : allProducts) {
            String productName = StringUtils.removeAccent(product.getTenSanPham()).toLowerCase();
            if (productName.contains(normalizedQuery)) {
                searchResults.add(product);
            }
        }

        // Cập nhật RecyclerView
        sanPhamAdapter.notifyDataSetChanged();
    }





}