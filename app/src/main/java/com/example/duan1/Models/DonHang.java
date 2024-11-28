package com.example.duan1.Models;

import java.util.Date;

public class DonHang {
    private int maDonHang; // PRIMARY KEY
    private int maNguoiDung;
    private Date ngayDat; // DATE
    private String trangThai; // VARCHAR(50)
    private float tongTien; // DECIMAL(10, 2)

    public DonHang(int maDonHang, int maNguoiDung, Date ngayDat, String trangThai, float tongTien) {
        this.maDonHang = maDonHang;
        this.maNguoiDung = maNguoiDung;
        this.ngayDat = ngayDat;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}
