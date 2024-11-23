package com.example.duan1.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class SanPham {
    private int maSanPham; // PRIMARY KEY
    private String tenSanPham; // VARCHAR(255)
    private float gia; // DECIMAL(10, 2)
    private String moTa; // TEXT
    private int maDanhMuc; // INT
    private int soLuong; // INT
    private String anh; //BLOB

    public SanPham() {
    }

    public SanPham(int maSanPham, String tenSanPham, float gia, String moTa, int maDanhMuc, int soLuong, String anh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.moTa = moTa;
        this.maDanhMuc = maDanhMuc;
        this.soLuong = soLuong;
        this.anh = anh;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getAnh() {
        return  anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

}
