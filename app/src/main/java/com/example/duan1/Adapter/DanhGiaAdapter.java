package com.example.duan1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duan1.Models.DanhGia;
import com.example.duan1.R;

import java.util.ArrayList;

public class DanhGiaAdapter extends BaseAdapter {


    private Context context;
    ArrayList<DanhGia> list;
    public DanhGiaAdapter(Context context, ArrayList<DanhGia> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_danhgia, null);
        TextView tvDanhGia = convertView.findViewById(R.id.tvDanhGia);
        tvDanhGia.setText(list.get(position).getDanhGia());
        return convertView;
    }
}
