package com.example.duan1.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;

public class SanPham implements Parcelable{
    private int maSanPham; // PRIMARY KEY
    private String tenSanPham; // VARCHAR(255)
    private float gia; // DECIMAL(10, 2)
    private String moTa; // TEXT
    private int maDanhMuc; // INT
    private int soLuong; // INT
    private String anh; //BLOB
    private Boolean isYeuThich;

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

    public Boolean getYeuThich() {
        return isYeuThich;
    }

    public void setYeuThich(Boolean yeuThich) {
        isYeuThich = yeuThich;
    }
    // Constructor đọc từ Parcel
    protected SanPham(Parcel in) {
        maSanPham = in.readInt();
        tenSanPham = in.readString();
        gia = in.readFloat();
        moTa = in.readString();
        maDanhMuc = in.readInt();
        soLuong = in.readInt();
        anh = in.readString();
        byte isYeuThichByte = in.readByte();
        isYeuThich = (isYeuThichByte == 1);
    }

    // Ghi dữ liệu vào Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(maSanPham);
        dest.writeString(tenSanPham);
        dest.writeFloat(gia);
        dest.writeString(moTa);
        dest.writeInt(maDanhMuc);
        dest.writeInt(soLuong);
        dest.writeString(anh);
        dest.writeByte((byte) (isYeuThich != null && isYeuThich ? 1 : 0)); // Kiểm tra null
    }

    // Phương thức mô tả nội dung (thường không dùng, trả về 0)
    @Override
    public int describeContents() {
        return 0;
    }

    // Creator để phục hồi đối tượng từ Parcel
    public static final Parcelable.Creator<SanPham> CREATOR = new Parcelable.Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };
}
