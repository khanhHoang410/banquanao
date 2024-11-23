package com.example.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duan1.Models.NguoiDung;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    Context context;
    ArrayList<NguoiDung> list;

    public NguoiDungAdapter(Context context, ArrayList<NguoiDung> list) {
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
        convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.item_nguoi_dung, parent, false);
        TextView tv_tenNguoiDung = convertView.findViewById(R.id.tv_tenNguoiDung);
        TextView tv_email = convertView.findViewById(R.id.tv_email);
        TextView tv_diaChi = convertView.findViewById(R.id.tv_diaChi);
        TextView tv_sdt = convertView.findViewById(R.id.tv_sdt);
        tv_tenNguoiDung.setText("Tên người dùng : " + list.get(position).getTenNguoiDung());
        tv_email.setText("Email : " + list.get(position).getEmail());
        tv_diaChi.setText("Địa chỉ : " + list.get(position).getDiaChi());
        tv_sdt.setText("Số điện thoại : " + list.get(position).getSdt());
        return convertView;
    }
}
