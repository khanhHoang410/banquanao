package com.example.duan1.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1.Database.DbHelper;

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
}