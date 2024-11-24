package com.example.duan1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Models.NguoiDung;

import java.util.ArrayList;

public class NguoiDungDAO {
    private DbHelper dbHelper;

    public NguoiDungDAO(Context context) {
        dbHelper = new DbHelper(context);
    }


    // Kiểm tra đăng nhập
    public boolean KiemTraDangNhap(String email, String password) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        boolean isLoggedIn = false;
        try {
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM NguoiDung WHERE email = ? AND matKhau = ?", new String[]{email, password});
            isLoggedIn = cursor.getCount() > 0; // Nếu có bản ghi thì đăng nhập thành công
        } finally {
            if (cursor != null) {
                cursor.close(); // Đảm bảo đóng con trỏ
            }
            sqLiteDatabase.close(); // Đóng cơ sở dữ liệu
        }

        return isLoggedIn; // Trả về true hoặc false
    }
    public boolean register(String tennguoidung,String email, String password , int sdt,String diachi) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenNguoiDung", tennguoidung);
        values.put("email", email);
        values.put("matKhau", password);
        values.put("sdt", sdt);
        values.put("diaChi", diachi);
        values.put("role", 1);
        long result = sqLiteDatabase.insert("NguoiDung", null, values);
        sqLiteDatabase.close();
        return result != -1;
    }
    public ArrayList<NguoiDung> getAll() {
        ArrayList<NguoiDung> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT tenNguoiDung,email,diaChi,sdt  FROM NguoiDung", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new NguoiDung(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matKhau", newPassword);

        // Cập nhật mật khẩu nếu mật khẩu cũ khớp
        int rowsAffected = sqLiteDatabase.update("NguoiDung", values, "email = ? AND matKhau = ?", new String[]{email, oldPassword});
        sqLiteDatabase.close();
        return rowsAffected > 0; // Trả về true nếu cập nhật thành công
    }
    public NguoiDung getUserByEmail(String email){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        NguoiDung nguoiDung = null;
        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.rawQuery("SELECT tenNguoiDung, email, diaChi, sdt FROM NguoiDung WHERE email = ?", new String[]{email});

            if (cursor != null && cursor.moveToFirst()) {
                // Lấy thông tin người dùng từ cursor
                String tenNguoiDung = cursor.getString(0);
                String email1 = cursor.getString(1);
                String diaChi = cursor.getString(2);
                int sdt = cursor.getInt(3);

                nguoiDung = new NguoiDung(tenNguoiDung, email1, diaChi, sdt);
            }
        } finally {
            if (cursor != null) {
                cursor.close(); // Đảm bảo đóng con trỏ
            }
            sqLiteDatabase.close(); // Đóng cơ sở dữ liệu
        }

        return nguoiDung;
    }
    public boolean updateUser(String tenNguoiDung, String sdt, String diaChi, String email) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenNguoiDung", tenNguoiDung);
        values.put("sdt", sdt);
        values.put("diaChi", diaChi);

        // Cập nhật thông tin người dùng theo email
        int rowsAffected = sqLiteDatabase.update("NguoiDung", values, "email = ?", new String[]{email});
        sqLiteDatabase.close();
        return rowsAffected > 0; // Trả về true nếu cập nhật thành công
    }

}