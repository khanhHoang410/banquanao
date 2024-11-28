package com.example.duan1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duan1.Adapter.HotSaleAdapter;
import com.example.duan1.Dao.HotSaleDAO;
import com.example.duan1.Models.SanPham;

import java.util.ArrayList;


public class BlankFragment2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        HotSaleDAO hotSaleDAO = new HotSaleDAO(getContext());
        ArrayList<SanPham> list = (ArrayList<SanPham>) hotSaleDAO.gettop10Hot();
        HotSaleAdapter hotSaleAdapter = new HotSaleAdapter(getContext(),list);
        ListView lvHotSale = view.findViewById(R.id.lvHotSale);
        lvHotSale.setAdapter(hotSaleAdapter);


    }
}