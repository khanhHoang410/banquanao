package com.example.duan1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.Dao.NguoiDungDAO;

public class ChangePasswordActivity extends AppCompatActivity {

    private NguoiDungDAO nguoiDungDAO;
    private EditText edOldPassword, edNewPassword, edConfirmPassword;
    private Button btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edOldPassword = findViewById(R.id.edoldPass);
        edNewPassword = findViewById(R.id.edPassWord);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        nguoiDungDAO = new NguoiDungDAO(this);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String email = sharedPreferences.getString("email", null);
                String oldPassword = edOldPassword.getText().toString().trim();
                String newPassword = edNewPassword.getText().toString().trim();
                String confirmPassword = edConfirmPassword.getText().toString().trim();

                // Kiểm tra thông tin đầu vào
                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu mới không khớp.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gọi phương thức để thay đổi mật khẩu
                boolean isChanged = nguoiDungDAO.changePassword(email, oldPassword, newPassword);
                if (isChanged) {
                    Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu cũ không chính xác.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}