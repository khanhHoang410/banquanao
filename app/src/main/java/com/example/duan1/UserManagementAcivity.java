package com.example.duan1;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Adapter.NguoiDungAdapter;
import com.example.duan1.Dao.NguoiDungDAO;
import com.example.duan1.Models.NguoiDung;

import java.util.ArrayList;

public class UserManagementAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_management_acivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listView = findViewById(R.id.lvNguoiDung);

        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        ArrayList<NguoiDung> list = nguoiDungDAO.getAll();
        NguoiDungAdapter adapter = new NguoiDungAdapter(this,list);
        listView.setAdapter(adapter);

    }
}