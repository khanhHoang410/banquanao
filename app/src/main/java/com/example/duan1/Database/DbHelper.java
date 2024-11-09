package com.example.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "FaciwayDB";
    private static final int DB_VERSION= 1;

    // Bảng danh mục
    static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE DanhMuc (\n" +
            "    maDanhMuc INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenDanhMuc TEXT NOT NULL\n" +
            ");";

    // Bảng sản phẩm
    static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE SanPham (\n" +
            "    maSanPham INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenSanPham TEXT NOT NULL,\n" +
            "    gia INTEGER NOT NULL,\n" +
            "    moTa TEXT,\n" +
            "    anh TEXT,\n" +  // Thêm thuộc tính ảnh
            "    maDanhMuc INTEGER,\n" +
            "    FOREIGN KEY (maDanhMuc) REFERENCES DanhMuc(maDanhMuc)\n" +
            ");";

    // Bảng giỏ hàng
    static final String CREATE_TABLE_CART = "CREATE TABLE GioHang (\n" +
            "    maGioHang INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    maNguoiDung INTEGER NOT NULL,\n" +
            "    maSanPham INTEGER NOT NULL,\n" +
            "    soLuong INTEGER NOT NULL,\n" +
            "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung),\n" +
            "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)\n" +
            ");";

    // Bảng đơn hàng
    static final String CREATE_TABLE_ORDERS = "CREATE TABLE DonHang (\n" +
            "    maDonHang INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    maNguoiDung INTEGER NOT NULL,\n" +
            "    ngayDat DATE NOT NULL,\n" +
            "    tongTien INTEGER NOT NULL,\n" +
            "    trangThai TEXT NOT NULL,\n" +
            "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung)\n" +
            ");";

    // Bảng chi tiết đơn hàng
    static final String CREATE_TABLE_ORDER_ITEMS = "CREATE TABLE ChiTietDonHang (\n" +
            "    maChiTietDonHang INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    maDonHang INTEGER NOT NULL,\n" +
            "    maSanPham INTEGER NOT NULL,\n" +
            "    soLuong INTEGER NOT NULL,\n" +
            "    gia INTEGER NOT NULL,\n" +
            "    FOREIGN KEY (maDonHang) REFERENCES DonHang(maDonHang),\n" +
            "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)\n" +
            ");";
    static final String DanhGia = "CREATE TABLE DanhGia (\n" +
            "    idDanhGia INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    maNguoiDung INTEGER ,\n" +
            "    maSanPham INTEGER ,\n" +
            "  BinhLuan TEXT " +
            ");";

    public DbHelper(@Nullable Context context){
        super(context, DB_Name,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_ORDER_ITEMS);
        db.execSQL(DanhGia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Categories");
        db.execSQL("DROP TABLE IF EXISTS Products");
        db.execSQL("DROP TABLE IF EXISTS Cart");
        db.execSQL("DROP TABLE IF EXISTS Orders");
        db.execSQL("DROP TABLE IF EXISTS OrderItems");
        onCreate(db);
    }
}
