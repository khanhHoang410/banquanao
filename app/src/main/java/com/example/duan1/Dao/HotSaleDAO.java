package com.example.duan1.Dao;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class HotSaleDAO {
    private SQLiteDatabase db;
    public HotSaleDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<SanPham> getData(String sql, String...selectionArgs) {
        List<SanPham> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            SanPham obj = new SanPham();
            obj.setMaSanPham(c.getInt(c.getColumnIndex("maSanPham")));
            obj.setTenSanPham(c.getString(c.getColumnIndex("tenSanPham")));
            obj.setGia(c.getFloat(c.getColumnIndex("gia")));
            obj.setMoTa(c.getString(c.getColumnIndex("moTa")));
            obj.setMaDanhMuc(c.getInt(c.getColumnIndex("maDanhMuc")));
            obj.setSoLuong(c.getInt(c.getColumnIndex("soLuong")));
            obj.setAnh(c.getString(c.getColumnIndex("anh")));
            obj.setYeuThich(c.getInt(c.getColumnIndex("isYeuThich"))==0?false:true);
            list.add(obj);
        }
        c.close();
        return list;
    }
    public List<SanPham> gettop10Hot(){
        String sql = "SELECT * FROM SanPham\n" +
                "ORDER BY soLuong ASC\n" +
                "LIMIT 10;";
        return getData(sql);
    }
}
