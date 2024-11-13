package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    EditText edEmail, edPassWord, edConfirmPassword;
    Button btnSignUp;

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
        edEmail = findViewById(R.id.edEmail);
        edPassWord = findViewById(R.id.edPassWord);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Nút đăng ký
        btnSignUp.setOnClickListener(v -> {
            String email = edEmail.getText().toString().trim();
            String password = edPassWord.getText().toString().trim();
            String confirmPassword = edConfirmPassword.getText().toString().trim();

            if (validateInput(email, password, confirmPassword)) {
                // Xử lý logic đăng ký tại đây (gửi dữ liệu lên server)
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                // Chuyển đến màn hình chính sau khi đăng ký
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
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