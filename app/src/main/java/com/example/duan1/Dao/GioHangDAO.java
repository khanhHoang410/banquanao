package com.example.duan1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Models.GioHang;

import java.util.ArrayList;
import java.util.List;

public class GioHangDAO {

    private SQLiteDatabase db;

    public GioHangDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db= dbHelper.getWritableDatabase();
    }

    public long insert(GioHang gioHang) {
        ContentValues values = new ContentValues();
        values.put("maDonHang", gioHang.getMaDonHang());
        values.put("maSanPham", gioHang.getMaSanPham());
        values.put("tongTien", gioHang.getTongTien());
        return db.insert("GioHang", null, values);
    }

    public int update(GioHang gioHang) {
        ContentValues values = new ContentValues();
        values.put("maDonHang", gioHang.getMaDonHang());
        values.put("maSanPham", gioHang.getMaSanPham());
        values.put("tongTien", gioHang.getTongTien());
        return db.update("GioHang", values, "maGioHang = ?", new String[]{String.valueOf(gioHang.getMaGioHang())});
    }

    public int delete(int maGioHang) {
        return db.delete("GioHang", "maGioHang = ?", new String[]{String.valueOf(maGioHang)});
    }

    public List<GioHang> getAll(){
        List<GioHang> gioHangList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM GioHang", null);
        while (cursor.moveToNext()) {
            GioHang gioHang = new GioHang();
            gioHang.setMaGioHang(cursor.getInt(cursor.getColumnIndexOrThrow("maGioHang")));
            gioHang.setMaDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang")));
            gioHang.setMaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow("maSanPham")));
            gioHang.setTongTien((float) cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien")));
            gioHangList.add(gioHang);
        }
        cursor.close();
        return gioHangList;
    }

    public GioHang getById(int maGioHang) {
        Cursor cursor = db.rawQuery("SELECT * FROM GioHang WHERE maGioHang = ?", new String[]{String.valueOf(maGioHang)});
        if (cursor.moveToFirst()) {
            GioHang gioHang = new GioHang();
            gioHang.setMaGioHang(cursor.getInt(cursor.getColumnIndexOrThrow("maGioHang")));
            gioHang.setMaDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang")));
            gioHang.setMaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow("maSanPham")));
            gioHang.setTongTien((float) cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien")));
            cursor.close();
            return gioHang;
        }
        cursor.close();
        return null;
    }
}