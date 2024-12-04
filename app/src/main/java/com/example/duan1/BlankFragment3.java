package com.example.duan1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1.Dao.DonHangDAO;
import com.example.duan1.Models.DonHang;

import java.util.List;


public class BlankFragment3 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank3, container, false);
    }
    int userId = getArguments().getInt("userId", -1);
    DonHangDAO donHangDAO = new DonHangDAO(requireContext());
    List<DonHang> donHangList = donHangDAO.getByUserId(userId);


}