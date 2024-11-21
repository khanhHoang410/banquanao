package com.example.duan1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;



public class BlankFragment4 extends Fragment {

    LinearLayout layoutofadmin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        layoutofadmin = view.findViewById(R.id.layoutofadmin);
        // Trong Fragment
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null); // null là giá trị mặc định nếu không tìm thấy

        if ("admin@gmail.com".equals(email)) { // Sử dụng .equals() để so sánh chuỗi
            layoutofadmin.setVisibility(View.VISIBLE); // Hiện layout cho admin
        } else {
            layoutofadmin.setVisibility(View.INVISIBLE); // Ẩn layout cho người dùng
        }
    }
}