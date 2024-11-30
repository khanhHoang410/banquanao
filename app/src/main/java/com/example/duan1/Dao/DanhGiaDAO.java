package com.example.duan1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Models.DanhGia;

import java.util.ArrayList;
import java.util.List;

public class DanhGiaDAO {
    private DbHelper dbHelper; // Thêm biến dbHelper
    private SQLiteDatabase db;

    public DanhGiaDAO(Context context) {
        dbHelper = new DbHelper(context); // Khởi tạo DbHelper
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertDanhGia(int maSanPham, int maNguoiDung, String danhGia, float diem) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSanPham", maSanPham);
        values.put("maNguoiDung", maNguoiDung); // Thêm maNguoiDung
        values.put("danhGia", danhGia);
        values.put("diem", diem); // Thêm diem
        long kq = db.insert("DanhGia", null, values);
        db.close();
        return kq != -1;
    }
    public ArrayList<DanhGia> getAll(int maSanPham) {
        ArrayList<DanhGia> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT danhGia FROM DanhGia WHERE maSanPham = ?",
                new String[]{String.valueOf(maSanPham)});

        if (cursor.moveToFirst()) { // Check if there are results
            do {
                DanhGia danhGia = new DanhGia();
                danhGia.setDanhGia(cursor.getString(0));
                list.add(danhGia);
            } while (cursor.moveToNext()); // Move to next item
        }

        cursor.close();
        db.close();
        return list;
    }

}