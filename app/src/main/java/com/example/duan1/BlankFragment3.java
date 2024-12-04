package com.example.duan1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1.Adapter.DonHangAdapter;
import com.example.duan1.Dao.DonHangDAO;
import com.example.duan1.Models.DonHang;

import java.util.List;


public class BlankFragment3 extends Fragment {
    RecyclerView recycler_view3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank3, container, false);

        int userId = getArguments().getInt("userId", -1);
        Log.d("BlankFragment3", "userId: " + userId);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int userId2 = sharedPreferences.getInt("maNguoidung", -1);
        DonHangDAO donHangDAO = new DonHangDAO(requireContext());
        List<DonHang> donHangList = donHangDAO.getByUserId(userId2);
        Log.d("TAG", "fragment3: " + donHangList.size());
            recycler_view3 = view.findViewById(R.id.recycler_view3);
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
            recycler_view3.setLayoutManager(layoutManager);
            DonHangAdapter adapter = new DonHangAdapter(requireContext(), donHangList);
        recycler_view3.setAdapter(adapter);

        return  view;
    }

}