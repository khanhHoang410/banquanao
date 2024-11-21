package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BlankFragment4 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank4, container, false);
    }

    // Phương thức này sẽ được gọi khi người dùng nhấn vào biểu tượng Support
    public void openMessagePage(View view) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        startActivity(intent);
    }
}