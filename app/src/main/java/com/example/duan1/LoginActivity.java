package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail, edPassWord;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edEmail = findViewById(R.id.edEmail);
        edPassWord = findViewById(R.id.edPassWord);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String password = edPassWord.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(LoginActivity.this, "Định dạng email không hợp lệ !", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý đăng nhập ở đây (gửi dữ liệu lên server, kiểm tra...)
                    boolean loginSuccess = checkLoginCredentials(email, password); // Giả sử có hàm checkLoginCredentials

                    if (loginSuccess) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                        // Điều hướng sang màn hình chính
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean checkLoginCredentials(String email, String password) {
        // Thực hiện kiểm tra thông tin đăng nhập
        // Gửi yêu cầu lên server và kiểm tra ở đây
        // Trả về true nếu thành công, false nếu thất bại
        return true;
    }
}