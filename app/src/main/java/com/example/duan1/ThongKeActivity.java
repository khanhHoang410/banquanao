package com.example.duan1;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Adapter.HotSaleAdapter;
import com.example.duan1.Dao.HotSaleDAO;
import com.example.duan1.Database.DbHelper;
import com.example.duan1.Models.SanPham;

import java.util.ArrayList;
import java.util.Calendar;

public class ThongKeActivity extends AppCompatActivity {

    private EditText edtNgay, edtNgay1;
    private TextView txtThongKe;
    DbHelper dbHelper;
    LinearLayout layoutBanChay ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_ke);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        layoutBanChay = findViewById(R.id.layoutBanChay);
        dbHelper = new DbHelper(this);
        ImageView imgLich = findViewById(R.id.img_lich);
        edtNgay = findViewById(R.id.edt_ngay);
        ImageView imgLich1 = findViewById(R.id.img_lich1);
        edtNgay1 = findViewById(R.id.edt_ngay1);
        txtThongKe = findViewById(R.id.txt_thong_ke);
        HotSaleDAO hotSaleDAO = new HotSaleDAO(this);
        ArrayList<SanPham> list = (ArrayList<SanPham>) hotSaleDAO.gettop10Hot();
        HotSaleAdapter hotSaleAdapter = new HotSaleAdapter(this,list);
        ListView lvHotSale = findViewById(R.id.lvHotSale);
        lvHotSale.setAdapter(hotSaleAdapter);
        imgLich.setOnClickListener(v -> {
            showDatePicker(edtNgay);
        });

        imgLich1.setOnClickListener(v -> {
            showDatePicker(edtNgay1);
        });

        // Thêm nút để thực hiện thống kê
        findViewById(R.id.btn_thong_ke).setOnClickListener(v -> {
            String tuNgay = edtNgay.getText().toString();
            String denNgay = edtNgay1.getText().toString();
            if (!tuNgay.isEmpty() && !denNgay.isEmpty()) {
                thongKeDonHangTheoKhoangThoiGian(tuNgay, denNgay);
                layoutBanChay.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePicker(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Định dạng ngày theo chuẩn yyyy-MM-dd
                    String formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                    editText.setText(formattedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }

    public void thongKeDonHangTheoKhoangThoiGian(String tuNgay, String denNgay) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT COUNT(*) AS SoDonHang, SUM(tongTien) AS TongTien " +
                "FROM DonHang " +
                "WHERE ngayDat BETWEEN ? AND ?";

        Cursor cursor = db.rawQuery(query, new String[]{tuNgay, denNgay});

        if (cursor.moveToFirst()) {
            int soDonHang = cursor.getInt(cursor.getColumnIndex("SoDonHang"));
            double tongTien = cursor.getDouble(cursor.getColumnIndex("TongTien"));

            // Hiển thị kết quả thống kê
            txtThongKe.setText("Số đơn hàng: " + soDonHang + "\nTổng tiền: " + tongTien + "VND" + "\nSố tiền lãi : " + (tongTien - tongTien/100*30) + "VND");
        } else {
            txtThongKe.setText("Không có đơn hàng trong khoảng thời gian này.");
        }

        cursor.close();
        db.close();
    }
}