package com.example.duan1;

import android.annotation.SuppressLint;
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
            if (email.isEmpty()) {
                emailInput.setError("Vui lòng nhập email");
            } else {
                // Gửi yêu cầu đặt lại mật khẩu ở đây
                Toast.makeText(this, "Đã gửi yêu cầu đặt lại mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý sự kiện nhấn vào "Quay lại Đăng Nhập"
        backToLogin.setOnClickListener(view -> {
            finish(); // Quay lại màn hình đăng nhập
        });
    }
}