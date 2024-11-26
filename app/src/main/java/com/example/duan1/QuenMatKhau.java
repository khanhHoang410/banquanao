package com.example.duan1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuenMatKhau extends AppCompatActivity {

    EditText emailInput;
    Button submitButton;
    TextView backToLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quen_mat_khau);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailInput = findViewById(R.id.edEmailForgot);
        submitButton = findViewById(R.id.btnSubmitForgot);
        backToLogin = findViewById(R.id.backToLogin);

        // Xử lý sự kiện nhấn nút Gửi
        submitButton.setOnClickListener(view -> {
            String email = emailInput.getText().toString();
            // Kiểm tra email hợp lệ và gửi yêu cầu đặt lại mật khẩu
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs2", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", email);
            editor.apply();
            if (email.isEmpty()) {
                emailInput.setError("Vui lòng nhập email");
            } else {
                // Tạo mã xác nhận ngẫu nhiên
                String verificationCode = generateVerificationCode();
                String subject = "Mã xác nhận quên mật khẩu";
                String message = "Mã xác nhận của bạn là: " + verificationCode;

                // Gửi email chứa mã xác nhận
                new SendMailTask(email, subject, message).execute();

                // Chuyển đến màn hình nhập mã xác nhận
                Intent intent = new Intent(QuenMatKhau.this, ResetPasswordActivity.class);
                intent.putExtra("VERIFICATION_CODE", verificationCode);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện nhấn vào "Quay lại Đăng Nhập"
        backToLogin.setOnClickListener(view -> {
            finish(); // Quay lại màn hình đăng nhập
        });
    }

    // Phương thức tạo mã xác nhận ngẫu nhiên
    private String generateVerificationCode() {
        int code = (int) (Math.random() * 900000) + 100000; // Tạo mã 6 chữ số
        return String.valueOf(code);
    }
}