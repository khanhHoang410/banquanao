package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.duan1.Models.GioHang;

import java.util.Date;
import java.util.List;

public class Nhapthongtinvadiachi extends AppCompatActivity {
    TextView tvOrderTotal,tvTotalAmount,tvReturnToCart;
    EditText etEmail,etFirstName,etLastName,etAddressLine1,etPhoneNumber,etCity;
    Button btnContinueToShipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nhapthongtinvadiachi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvOrderTotal = findViewById(R.id.tvOrderTotal);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        tvReturnToCart = findViewById(R.id.tvReturnToCart);
        etEmail = findViewById(R.id.etEmail);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAddressLine1 = findViewById(R.id.etAddressLine1);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etCity = findViewById(R.id.etCity);
        btnContinueToShipping = findViewById(R.id.btnContinueToShipping);

        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0);
        tvOrderTotal.setText("$"+totalPrice);
        tvTotalAmount.setText("$"+totalPrice);
        tvReturnToCart.setOnClickListener(v -> finish());

        btnContinueToShipping.setOnClickListener(v->{
            if (validateInput()){
                String firstName = etFirstName.getText().toString().trim();
                String address = etAddressLine1.getText().toString().trim();
                String phoneNumber = etPhoneNumber.getText().toString().trim();

                int maGioHang = getIntent().getIntExtra("maGioHang", -1);
                int maNguoiDung = getIntent().getIntExtra("maNguoiDung", -1);
                GioHangDAO gioHangDAO = new GioHangDAO(this);
                List<GioHang> gioHangList = gioHangDAO.getAll(maNguoiDung);

                // tạo đối tượng đơn hàng mới
                DonHang donHang = new DonHang();
                donHang.setMaNguoiDung(maNguoiDung);
                donHang.setTen(firstName);
                donHang.setDiaChi(address);
                donHang.setPhoneNumber(phoneNumber);
                donHang.setTongTien((float) totalPrice);
                donHang.setMaGioHang(maGioHang);
                donHang.setNgayDat(new Date());
                DonHangDAO donHangDAO = new DonHangDAO(this);
                long result = donHangDAO.insert(donHang);

                if (result>0){
                    // insert thành công
                    int maDonHang= (int) result; // lấy mã đơn hàng vừa insert
                    Log.d("Nhapthongtinvadiachi", "maDonHang: " + maDonHang);

                    // cập nhật madonhang cho cac giohang
                    for (GioHang gioHang : gioHangList){
                        gioHang.setMaDonHang(maDonHang);
                        gioHangDAO.update(gioHang);
                    }
                    Toast.makeText(this, "insert vào đơn hàng thành công", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this,Xacnhanthongtindiachi.class);
                    intent.putExtra("maDonHang", maDonHang);
                    startActivity(intent);
                }else {
                    Log.d("Nhapthongtinvadiachi", "insert failed");
                }
            }
        });

    }
    private boolean validateInput() {
        String email = etEmail.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String address = etAddressLine1.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String city = etCity.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Vui lòng nhập email");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email không hợp lệ");
            return false;
        }
        if (firstName.isEmpty()) {
            etFirstName.setError("Vui lòng nhập tên");
            return false;
        }
        if (lastName.isEmpty()) {
            etLastName.setError("Vui lòng nhập họ");
            return false;
        }
        if (address.isEmpty()) {
            etAddressLine1.setError("Vui lòng nhập địa chỉ");
            return false;
        }
        if (phoneNumber.isEmpty()) {
            etPhoneNumber.setError("Vui lòng nhập số điện thoại");
            return false;
        }
        if (!phoneNumber.matches("^[0-9]{10}$")) {
            etPhoneNumber.setError("Số điện thoại không hợp lệ");
            return false;
        }
        if (city.isEmpty()) {
            etCity.setError("Vui lòng nhập thành phố");
            return false;
        }


        return true;
    }



}