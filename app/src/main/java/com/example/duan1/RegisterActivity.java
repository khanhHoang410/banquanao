package com.example.duan1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Dao.NguoiDungDAO;

public class RegisterActivity extends AppCompatActivity {

    EditText edEmail, edPassWord, edConfirmPassword,edTenNguoiDung,edSdthoai,edDiaChi;
    Button btnSignUp;
    NguoiDungDAO nguoiDungDAO ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Khởi tạo các view từ layout
        nguoiDungDAO = new NguoiDungDAO(this);
        edEmail = findViewById(R.id.edEmail);
        edTenNguoiDung = findViewById(R.id.edTenNguoiDung);
        edPassWord = findViewById(R.id.edPassWord);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        edSdthoai = findViewById(R.id.edSdt);
        edDiaChi = findViewById(R.id.edDiaChi);
        // Nút đăng ký
        btnSignUp.setOnClickListener(v -> {
            String email = edEmail.getText().toString().trim();
            String password = edPassWord.getText().toString().trim();
            String tennguoidung = edTenNguoiDung.getText().toString().trim();
            String confirmPassword = edConfirmPassword.getText().toString().trim();
            String sdt = edSdthoai.getText().toString().trim();
            String diachi = edDiaChi.getText().toString().trim();
            if (validateInput(email, password, confirmPassword)) {
                boolean isRegistered = nguoiDungDAO.register(tennguoidung, email, password, sdt, diachi);
                if (isRegistered) {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
                // Xử lý logic đăng ký tại đây (gửi dữ liệu lên server)
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                // Chuyển đến màn hình chính sau khi đăng ký
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        edSdthoai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Không cần thực hiện gì ở đây
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Kiểm tra nếu người dùng đã xóa hết nội dung
                if (s.length() == 0) {
                    edSdthoai.setText("+84");
                    edSdthoai.setSelection(edSdthoai.getText().length()); // Đặt con trỏ ở cuối
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Không cần thực hiện gì ở đây
            }
        });

    }

    // Hàm kiểm tra dữ liệu đầu vào
    private boolean validateInput(String email, String password, String confirmPassword) {
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Vui lòng nhập email hợp lệ !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải chứa ít nhất 6 ký tự !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}