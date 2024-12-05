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
    private DbHelper dbHelper;
    public GioHangDAO(Context context) {
        dbHelper = new DbHelper(context);
        db= dbHelper.getWritableDatabase();
    }


    public long insert(GioHang gioHang) {
        ContentValues values = new ContentValues();
        values.put("maDonHang", gioHang.getMaDonHang());
        values.put("maSanPham", gioHang.getMaSanPham());
        values.put("maNguoiDung", gioHang.getMaNguoiDung()); // Thêm maNguoiDung
        values.put("tongTien", gioHang.getTongTien());
        return db.insert("GioHang", null, values);
    }

    public int update(GioHang gioHang) {
        ContentValues values = new ContentValues();
        values.put("maDonHang", gioHang.getMaDonHang());
        values.put("maSanPham", gioHang.getMaSanPham());
        values.put("maNguoiDung", gioHang.getMaNguoiDung()); // Thêm maNguoiDung
        values.put("tongTien", gioHang.getTongTien());
        return db.update("GioHang", values, "maGioHang = ?", new String[]{String.valueOf(gioHang.getMaGioHang())});
    }
    public int delete(int maGioHang) {
        return db.delete("GioHang", "maGioHang = ?", new String[]{String.valueOf(maGioHang)});
    }

    public List<GioHang> getAll(int userId) {
        List<GioHang> gioHangList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GioHang WHERE maNguoiDung = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            do {
                GioHang gioHang = new GioHang();
                gioHang.setMaGioHang(cursor.getInt(cursor.getColumnIndexOrThrow("maGioHang")));
                gioHang.setMaDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang")));
                gioHang.setMaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow("maSanPham")));
                gioHang.setMaNguoiDung(cursor.getInt(cursor.getColumnIndexOrThrow("maNguoiDung")));
                gioHang.setTongTien(cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien"))); // Sử dụng getDouble cho tongTien
                gioHangList.add(gioHang);
            } while (cursor.moveToNext());
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
            gioHang.setMaNguoiDung(cursor.getInt(cursor.getColumnIndexOrThrow("maNguoiDung"))); // Lấy maNguoiDung
            gioHang.setTongTien((float) cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien")));
            cursor.close();
            return gioHang;
        }
        cursor.close();
        return null;
    }
    public GioHang getGioHangByMaSanPhamAndMaNguoiDung(int maSanPham, int maNguoiDung) {
        String query = "SELECT * FROM GioHang WHERE maSanPham = ? AND maNguoiDung = ?";
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(maSanPham), String.valueOf(maNguoiDung)});

        if (cursor.moveToFirst()) {
            GioHang gioHang = new GioHang();
            gioHang.setMaGioHang(cursor.getInt(cursor.getColumnIndexOrThrow("maGioHang")));
            gioHang.setMaDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang")));
            gioHang.setMaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow("maSanPham")));
            gioHang.setMaNguoiDung(cursor.getInt(cursor.getColumnIndexOrThrow("maNguoiDung")));
            gioHang.setTongTien(cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien")));
            cursor.close();
            return gioHang;
        }

        cursor.close();
        return null;
    }
    public double calculateTotalPrice(int maNguoiDung) {
        double totalPrice = 0;

        List<GioHang> gioHangList = getAll(maNguoiDung);

        for (GioHang gioHang : gioHangList) {
            totalPrice += gioHang.getTongTien();}
        return totalPrice;
    }

    public int deleteByUserId(int maNguoiDung) {
        return db.delete("GioHang", "maNguoiDung = ?", new String[]{String.valueOf(maNguoiDung)});
    }
}
