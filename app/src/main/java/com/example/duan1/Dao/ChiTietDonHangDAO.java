package com.example.duan1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Models.ChiTietDonHang;

import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public ChiTietDonHangDAO(Context context) {
        dbHelper =new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ChiTietDonHang chiTietDonHang) {
        ContentValues values = new ContentValues();
        values.put("maDonHang", chiTietDonHang.getMaDonHang());
        values.put("maSanPham", chiTietDonHang.getMaSanPham());
        values.put("soLuong", chiTietDonHang.getSoLuong());
        values.put("gia", chiTietDonHang.getGia());
        values.put("maKichThuoc", chiTietDonHang.getMaKichThuoc());
        return db.insert("ChiTietDonHang", null, values);
    }

    public int update(ChiTietDonHang chiTietDonHang) {
        ContentValues values = new ContentValues();
        values.put("maDonHang", chiTietDonHang.getMaDonHang());
        values.put("maSanPham", chiTietDonHang.getMaSanPham());
        values.put("soLuong", chiTietDonHang.getSoLuong());
        values.put("gia", chiTietDonHang.getGia());values.put("maKichThuoc", chiTietDonHang.getMaKichThuoc());
        return db.update("ChiTietDonHang", values, "maChiTietDonHang = ?", new String[]{String.valueOf(chiTietDonHang.getMaChiTietDonHang())});
    }

    public int delete(int maChiTietDonHang) {
        return db.delete("ChiTietDonHang", "maChiTietDonHang = ?", new String[]{String.valueOf(maChiTietDonHang)});
    }

    public List<ChiTietDonHang> getAll() {
        List<ChiTietDonHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM ChiTietDonHang", null);
        if (cursor.moveToFirst()) {
            do {
                ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
                chiTietDonHang.setMaChiTietDonHang(cursor.getInt(0));
                chiTietDonHang.setMaDonHang(cursor.getInt(1));
                chiTietDonHang.setMaSanPham(cursor.getInt(2));
                chiTietDonHang.setSoLuong(cursor.getInt(3));
                chiTietDonHang.setGia(cursor.getDouble(4));
                chiTietDonHang.setMaKichThuoc(cursor.getInt(5));
                list.add(chiTietDonHang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public ChiTietDonHang getById(int maChiTietDonHang) {
        String sql = "SELECT * FROM ChiTietDonHang WHERE maChiTietDonHang = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(maChiTietDonHang)});
        if (cursor.moveToFirst()) {
            ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
            chiTietDonHang.setMaChiTietDonHang(cursor.getInt(0));
            chiTietDonHang.setMaDonHang(cursor.getInt(1));
            chiTietDonHang.setMaSanPham(cursor.getInt(2));
            chiTietDonHang.setSoLuong(cursor.getInt(3));
            chiTietDonHang.setGia(cursor.getDouble(4));
            chiTietDonHang.setMaKichThuoc(cursor.getInt(5));
            cursor.close();
            return chiTietDonHang;
        }
        cursor.close();
        return null;
    }

    public List<ChiTietDonHang> getByMaDonHang(int maDonHang) {
        List<ChiTietDonHang> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietDonHang WHERE maDonHang = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(maDonHang)});
        if (cursor.moveToFirst()) {
            do {
                ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
                chiTietDonHang.setMaChiTietDonHang(cursor.getInt(0));
                chiTietDonHang.setMaDonHang(cursor.getInt(1));
                chiTietDonHang.setMaSanPham(cursor.getInt(2));
                chiTietDonHang.setSoLuong(cursor.getInt(3));
                chiTietDonHang.setGia(cursor.getDouble(4));
                chiTietDonHang.setMaKichThuoc(cursor.getInt(5));
                list.add(chiTietDonHang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}