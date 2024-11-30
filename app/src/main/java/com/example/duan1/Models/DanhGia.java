package com.example.duan1.Models;

public class DanhGia {
    private int maDanhGia; // PRIMARY KEY
    private int maSanPham;
    private int maNguoiDung;
    private String danhGia;
    private float diem; // Thay DECIMAL bằng float

    public DanhGia(int maSanPham, String danhGia) {
        this.maSanPham = maSanPham;
        this.danhGia = danhGia;
    }

    public DanhGia() {
    }

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
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

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}
