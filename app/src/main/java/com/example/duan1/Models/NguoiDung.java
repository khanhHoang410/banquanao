package com.example.duan1.Models;

public class NguoiDung {
    private int maNguoiDung; // PRIMARY KEY
    private String tenNguoiDung; // VARCHAR(255)
    private String email; // VARCHAR(255)
    private String matKhau; // VARCHAR(255)
    private String diaChi; // VARCHAR(255)
    private int sdt;
    private int role; // INT

    public NguoiDung(String tenNguoiDung, String email, String diaChi, int sdt) {
        this.tenNguoiDung = tenNguoiDung;
        this.email = email;
        this.diaChi = diaChi;
        this.sdt = sdt;

    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
}
