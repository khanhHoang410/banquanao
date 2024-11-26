package com.example.duan1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Dao.NguoiDungDAO;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText edCode, edNewPassword;
    private Button btnResetPassword;
    private String verificationCode; // Mã xác nhận đã gửi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        verificationCode = getIntent().getStringExtra("VERIFICATION_CODE");
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        edCode = findViewById(R.id.edCode);
        edNewPassword = findViewById(R.id.edNewPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        btnResetPassword.setOnClickListener(view -> {
            String inputCode = edCode.getText().toString().trim();
            String newPassword = edNewPassword.getText().toString().trim();

            if (inputCode.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mã xác nhận", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!inputCode.equals(verificationCode)) {
                Toast.makeText(this, "Mã xác nhận không đúng", Toast.LENGTH_SHORT).show();
                return;
            }



            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs2", MODE_PRIVATE);
            String email = sharedPreferences.getString("email", null);
            if (nguoiDungDAO.updatePass(email, newPassword)) {
                Toast.makeText(this, "Mật khẩu đã được đổi thành công", Toast.LENGTH_SHORT).show();
                finish(); // Quay lại màn hình trước
            } else {
                Toast.makeText(this, "Đã xảy ra lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Phương thức kiểm tra độ mạnh của mật khẩu
    private boolean isValidPassword(String password) {
        // Kiểm tra độ dài tối thiểu
        if (password.length() < 6) return false;

        // Kiểm tra có ít nhất một chữ cái, một số và một ký tự đặc biệt
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isWhitespace(c)) hasSpecialChar = true;
        }

        return hasLetter && hasDigit && hasSpecialChar;
    }
}