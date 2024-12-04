package com.example.duan1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Models.DonHang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DonHangDAO {

    private SQLiteDatabase db;
    private DbHelper dbHelper;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public DonHangDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(DonHang donHang) {
        ContentValues values = new ContentValues();
        values.put("maNguoiDung", donHang.getMaNguoiDung());
        values.put("ngayDat", sdf.format(donHang.getNgayDat()));
        values.put("trangThai", donHang.getTrangThai());
        values.put("tongTien", donHang.getTongTien());
        values.put("diaChi", donHang.getDiaChi());
        values.put("phoneNumber", donHang.getPhoneNumber());
        values.put("ten", donHang.getTen());
        values.put("maGioHang", donHang.getMaGioHang());
        return db.insert("DonHang", null, values);}

    public int update(DonHang donHang) {
        ContentValues values = new ContentValues();
        values.put("maNguoiDung", donHang.getMaNguoiDung());
        values.put("ngayDat", sdf.format(donHang.getNgayDat()));
        values.put("trangThai", donHang.getTrangThai());
        values.put("tongTien", donHang.getTongTien());
        values.put("diaChi", donHang.getDiaChi());
        values.put("phoneNumber", donHang.getPhoneNumber());
        values.put("ten", donHang.getTen());
        values.put("maGioHang", donHang.getMaGioHang());
        return db.update("DonHang", values, "maDonHang = ?", new String[]{String.valueOf(donHang.getMaDonHang())});
    }

    public int delete(int maDonHang) {
        return db.delete("DonHang", "maDonHang = ?", new String[]{String.valueOf(maDonHang)});
    }

    public List<DonHang> getAll() {
        List<DonHang> donHangList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM DonHang", null);
        if (cursor.moveToFirst()) {
            do {
                try {
                    DonHang donHang = new DonHang();
                    donHang.setMaDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang")));
                    donHang.setMaNguoiDung(cursor.getInt(cursor.getColumnIndexOrThrow("maNguoiDung")));
                    donHang.setNgayDat(sdf.parse(cursor.getString(cursor.getColumnIndexOrThrow("ngayDat"))));
                    donHang.setTrangThai(cursor.getString(cursor.getColumnIndexOrThrow("trangThai")));
                    donHang.setTongTien(cursor.getFloat(cursor.getColumnIndexOrThrow("tongTien")));
                    donHang.setDiaChi(cursor.getString(cursor.getColumnIndexOrThrow("diaChi")));
                    donHang.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow("phoneNumber")));
                    donHang.setTen(cursor.getString(cursor.getColumnIndexOrThrow("ten")));
                    donHang.setMaGioHang(cursor.getInt(cursor.getColumnIndexOrThrow("maGioHang")));donHangList.add(donHang);
                } catch (ParseException e) {
                    Log.e("DonHangDAO", "Lỗi khi phân tích ngày đặt hàng: " + e.getMessage());
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return donHangList;
    }

    public DonHang getById(int maDonHang) {
        Cursor cursor = db.rawQuery("SELECT * FROM DonHang WHERE maDonHang = ?", new String[]{String.valueOf(maDonHang)});
        if (cursor.moveToFirst()) {
            try {
                DonHang donHang = new DonHang();
                donHang.setMaDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang")));
                donHang.setMaNguoiDung(cursor.getInt(cursor.getColumnIndexOrThrow("maNguoiDung")));
                donHang.setNgayDat(sdf.parse(cursor.getString(cursor.getColumnIndexOrThrow("ngayDat"))));
                donHang.setTrangThai(cursor.getString(cursor.getColumnIndexOrThrow("trangThai")));
                donHang.setTongTien(cursor.getFloat(cursor.getColumnIndexOrThrow("tongTien")));
                donHang.setDiaChi(cursor.getString(cursor.getColumnIndexOrThrow("diaChi")));
                donHang.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow("phoneNumber")));
                donHang.setTen(cursor.getString(cursor.getColumnIndexOrThrow("ten")));
                donHang.setMaGioHang(cursor.getInt(cursor.getColumnIndexOrThrow("maGioHang")));
                cursor.close();
                return donHang;
            } catch (ParseException e) {
                Log.e("DonHangDAO", "Lỗi khi phân tích ngày đặt hàng: " + e.getMessage());
            }
        }
        cursor.close();
        return null;
    }
    public List<DonHang> getByUserId(int userId) {
        List<DonHang> donHangList = new ArrayList<>();
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DonHang WHERE maNguoiDung = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            do {
                try {
                    DonHang donHang = new DonHang();
                    donHang.setMaDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang")));
                    donHang.setMaNguoiDung(cursor.getInt(cursor.getColumnIndexOrThrow("maNguoiDung")));
                    donHang.setNgayDat(sdf.parse(cursor.getString(cursor.getColumnIndexOrThrow("ngayDat"))));
                    donHang.setTrangThai(cursor.getString(cursor.getColumnIndexOrThrow("trangThai")));
                    donHang.setTongTien(cursor.getFloat(cursor.getColumnIndexOrThrow("tongTien")));
                    donHang.setDiaChi(cursor.getString(cursor.getColumnIndexOrThrow("diaChi")));
                    donHang.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow("phoneNumber")));
                    donHang.setTen(cursor.getString(cursor.getColumnIndexOrThrow("ten")));
                    donHang.setMaGioHang(cursor.getInt(cursor.getColumnIndexOrThrow("maGioHang")));
                    donHangList.add(donHang);
                } catch (ParseException e) {
                    Log.e("DonHangDAO", "Lỗi khi phân tích ngày đặt hàng: " + e.getMessage());
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return donHangList;
    }

}