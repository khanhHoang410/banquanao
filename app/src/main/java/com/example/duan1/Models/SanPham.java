package com.example.duan1.Models;

import android.graphics.Bitmap;

public class SanPham {
    private int maSanPham; // PRIMARY KEY
    private String tenSanPham; // VARCHAR(255)
    private float gia; // DECIMAL(10, 2)
    private String moTa; // TEXT
    private int maDanhMuc; // INT
    private int soLuong; // INT
    private Bitmap anh; //BLOB
}
