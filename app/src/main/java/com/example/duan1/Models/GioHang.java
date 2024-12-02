package com.example.duan1.Models;

import java.io.Serializable;

public class GioHang implements Serializable {
    private int maGioHang; // PRIMARY KEY
    private int maDonHang;
    private int maSanPham;
    private int maNguoiDung; // Thêm cột maNguoiDung
    private double tongTien; // DECIMAL(10, 2)

    // Constructor đầy đủ
    public GioHang(int maGioHang, int maDonHang, int maSanPham, int maNguoiDung, double tongTien) {
        this.maGioHang = maGioHang;
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.maNguoiDung = maNguoiDung;
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

   
    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

   
    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}