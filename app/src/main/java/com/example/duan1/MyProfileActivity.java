package com.example.duan1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Dao.NguoiDungDAO;
import com.example.duan1.Models.NguoiDung;

public class MyProfileActivity extends AppCompatActivity {
        EditText nameInput, emailInput, phoneInput, addressInput;
        Button editButton ;
    NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        phoneInput = findViewById(R.id.phoneInput);
        addressInput = findViewById(R.id.addressInput);
        nguoiDungDAO = new NguoiDungDAO(this);
        editButton = findViewById(R.id.editButton);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        NguoiDung user = nguoiDungDAO.getUserByEmail(email);
        if (user != null) {
            nameInput.setText(user.getTenNguoiDung());
            emailInput.setText(user.getEmail());
            phoneInput.setText("+84"+String.valueOf(user.getSdt()));
            addressInput.setText(user.getDiaChi());
        } else {
            // Log hoặc thông báo người dùng không tìm thấy
            Toast.makeText(this, " Không tìm thấy người dùng", Toast.LENGTH_SHORT).show();
        }
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDialog();
            }
        });
    }
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_myprofile, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        EditText upNameInput = view.findViewById(R.id.upnameInput);
        EditText upEmailInput = view.findViewById(R.id.upemailInput);
        EditText upPhoneInput = view.findViewById(R.id.upphoneInput);
        EditText upAddressInput = view.findViewById(R.id.upaddressInput);
        Button submitButton = view.findViewById(R.id.SubmitButton);
        Button backButton = view.findViewById(R.id.BackButton);

        // Lấy thông tin người dùng hiện tại
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        NguoiDung user = nguoiDungDAO.getUserByEmail(email);

        if (user != null) {
            upNameInput.setText(user.getTenNguoiDung());
            upEmailInput.setText(user.getEmail()); // Không cho phép thay đổi email
            upPhoneInput.setText(String.valueOf(user.getSdt()));
            upAddressInput.setText(user.getDiaChi());
        }

        submitButton.setOnClickListener(v -> {
            String upName = upNameInput.getText().toString();
            String upPhone = upPhoneInput.getText().toString();
            String upAddress = upAddressInput.getText().toString();

            // Cập nhật thông tin người dùng mà không thay đổi email
            boolean isUpdated = nguoiDungDAO.updateUser(upName, upPhone, upAddress, email);
            if (isUpdated) {
                Toast.makeText(MyProfileActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                // Cập nhật lại thông tin trên giao diện chính
                nameInput.setText(upName);
                phoneInput.setText(upPhone);
                addressInput.setText(upAddress);
            } else {
                Toast.makeText(MyProfileActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> alertDialog.dismiss());
    }

}