package com.example.duan1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Dao.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText edEmail, edPassword;
    private Button btnSignIn;
    private TextView tvSignUp, tvForgotPassword;
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassWord);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.tv_sign_up);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        ImageView topImage = findViewById(R.id.topImage);
        Animation bounceAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_from_top);
        topImage.startAnimation(bounceAnim);
        // Khởi tạo DAO
        nguoiDungDAO = new NguoiDungDAO(this);

        // Xử lý sự kiện đăng nhập
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString().trim();
                String password = edPassword.getText().toString().trim();

                // Validate
                if (!validateInput(email, password)) {
                    return;
                }

                int maNguoiDung = nguoiDungDAO.KiemTraDangNhap(email, password);
                if (maNguoiDung != -1) {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("maNguoiDung", maNguoiDung); // Lưu mã người dùng
                    editor.putString("email", email);
                    editor.apply();
                    int userId = getCurrentUserId(); // lấy id người dùng hiện tại ngay sau khi lưu
                    Log.d("userId", "userId: " + userId);

                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userId",userId); // thêm
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không chính xác.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện chuyển đến màn hình đăng ký
        tvSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Xử lý sự kiện chuyển đến màn hình quên mật khẩu
        tvForgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, QuenMatKhau.class);
            startActivity(intent);
        });
    }

    // Hàm kiểm tra đầu vào
    private boolean validateInput(String email, String password) {
        if (email.isEmpty()) {
            edEmail.setError("Vui lòng nhập email.");
            edEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Email không hợp lệ.");
            edEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            edPassword.setError("Vui lòng nhập mật khẩu.");
            edPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            edPassword.setError("Mật khẩu phải có ít nhất 6 ký tự.");
            edPassword.requestFocus();
            return false;
        }

        return true;
    }
    public int getCurrentUserId(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("maNguoiDung", -1);
    }
}
