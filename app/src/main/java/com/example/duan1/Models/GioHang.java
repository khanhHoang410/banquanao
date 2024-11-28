package com.example.duan1.Models;

import java.io.Serializable;

public class GioHang implements Serializable {
    private int maGioHang; // PRIMARY KEY
    private int maDonHang;
    private int maSanPham;
    private float tongTien; // DECIMAL(10, 2)

    public GioHang(int maGioHang, int maDonHang, int maSanPham, float tongTien) {
        this.maGioHang = maGioHang;
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.tongTien = tongTien;
    }
    public GioHang() {
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}
