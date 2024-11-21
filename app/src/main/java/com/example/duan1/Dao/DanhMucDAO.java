package com.example.duan1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Models.DanhMuc;

import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO {
    private SQLiteDatabase db;

    public DanhMucDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        this.db = db;
    }
    public long insert(DanhMuc obj){
        ContentValues values = new ContentValues();
        values.put("tenDanhMuc",obj.getTenDanhMuc());
        return db.insert("DanhMuc",null,values);
    }
    public int update(DanhMuc obj){
        ContentValues values = new ContentValues();
        values.put("tenDanhMuc",obj.getTenDanhMuc());
        return db.update("DanhMuc",values,"maDanhMuc=?",new String[]{String.valueOf(obj.getMaDanhMuc())});
    }

    public int delete(int maDanhMuc){
        return db.delete("DanhMuc","maDanhMuc=?",new String[]{String.valueOf(maDanhMuc)});
    }

    @SuppressLint("Range")
    public List<DanhMuc> getData(String sql, String...selectionArgs) {
        List<DanhMuc> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            DanhMuc obj = new DanhMuc();
            obj.setMaDanhMuc(c.getInt(c.getColumnIndex("maDanhMuc")));
            obj.setTenDanhMuc(c.getString(c.getColumnIndex("tenDanhMuc")));
            list.add(obj);
        }
        return list;
    }

    public List<DanhMuc> getAll(){
        String sql = "SELECT * FROM DanhMuc";
        return getData(sql);

    }
    public DanhMuc getID(String id){
        String sql = "SELECT * FROM DanhMuc WHERE maDanhMuc = ?";
        List<DanhMuc> list = getData(sql,id);
        return list.get(0);
    }
}
