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
//        byte[] imageByteArray = getAnhByteArray();
//        return BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
        return  anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
//    public byte[] getAnhByteArray(){
//        if (anh!=null){
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            anh.compress(Bitmap.CompressFormat.PNG,100,stream);// Nén ảnh thành định dạng PNG hoặc JPG
//            return stream.toByteArray();// Trả về mảng byte
//        }
//        return new byte[0]; // Trả về mảng byte rỗng nếu không có ảnh
//    }
//    public void setAnhFromByteArray(byte[] anhByteArray) {
//        if (anhByteArray != null && anhByteArray.length > 0) {
//            this.anh = BitmapFactory.decodeByteArray(anhByteArray, 0, anhByteArray.length);
//        } else {
//            this.anh = null; // Không có dữ liệu thì đặt null
//        }
//
//    }
}
