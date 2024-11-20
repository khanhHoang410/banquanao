package com.example.duan1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Models.SanPham;

import java.util.ArrayList;
import java.util.List;

import kotlin.Suppress;

public class SanPhamDAO {
    private SQLiteDatabase db;

    public SanPhamDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(SanPham obj){
        ContentValues values = new ContentValues();
        values.put("tenSanPham", obj.getTenSanPham());
        values.put("gia", obj.getGia());
        values.put("moTa", obj.getMoTa());
        values.put("maDanhMuc", obj.getMaDanhMuc());
        values.put("soLuong", obj.getSoLuong());
        return db.insert("SanPham",null,values);
    }
    public int update(SanPham obj){
        ContentValues values = new ContentValues();
        values.put("tenSanPham", obj.getTenSanPham());
        values.put("gia", obj.getGia());
        values.put("moTa", obj.getMoTa());
        values.put("maDanhMuc", obj.getMaDanhMuc());
        values.put("soLuong", obj.getSoLuong());
        return db.update("SanPham",values,"maSanPham=?",new String[]{String.valueOf(obj.getMaSanPham())});
    }

    public int delete(int maSanPham){
        return db.delete("SanPham","maSanPham=?",new String[]{String.valueOf(maSanPham)});
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
            list.add(obj);
        }
        return list;
    }

    public List<SanPham> getAll(){
        String sql = "SELECT * FROM SanPham";
        return getData(sql);
    }

    public SanPham getID(String id){
        String sql = "SELECT * FROM SanPham WHERE maSanPham = ?";
        List<SanPham> list = getData(sql,id);
        return list.get(0);
    }

}
