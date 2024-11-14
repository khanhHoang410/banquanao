package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail, edPassWord;
    Button btnSignIn;
    TextView tvSignUp;
    TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_nhap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edEmail = findViewById(R.id.edEmail);
        edPassWord = findViewById(R.id.edPassWord);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.tv_sign_up);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        // Xử lý sự kiện nhấn "Sign Up"
        tvSignUp.setOnClickListener(view -> {
            Intent signUpIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(signUpIntent);
        });

        // Xử lý sự kiện nhấn "Forgot Password"
        tvForgotPassword.setOnClickListener(view -> {
            Intent forgotPasswordIntent = new Intent(LoginActivity.this, QuenMatKhau.class);
            startActivity(forgotPasswordIntent);
        });
    }
    private boolean checkLoginCredentials(String email, String password) {
        // Thực hiện kiểm tra thông tin đăng nhập
        // Gửi yêu cầu lên server và kiểm tra ở đây
        // Trả về true nếu thành công, false nếu thất bại
        return true;
    }
}