package com.example.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "FaciwayDB";
    private static final int DB_VERSION= 1;

    // Bảng danh mục


    public DbHelper( Context context){
        super(context, DB_Name,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String DanhMuc = "CREATE TABLE DanhMuc (\n" +
                "    maDanhMuc INT PRIMARY KEY autoincrement,\n" +
                "    tenDanhMuc VARCHAR(255)\n" +
                ");";

        String SanPham = "CREATE TABLE SanPham (\n" +
                "    maSanPham INT PRIMARY KEY autoincrement,\n" +
                "    tenSanPham VARCHAR(255),\n" +
                "    gia DECIMAL(10, 2),\n" +
                "    moTa TEXT,\n" +
                "    maDanhMuc INT,\n" +
                "    soLuong INT,\n" +
                "    anh BLOB,\n" +
                "    FOREIGN KEY (maDanhMuc) REFERENCES DanhMuc(maDanhMuc)\n" +
                ");";
        String KichThuoc = "CREATE TABLE KichThuoc (\n" +
                "    maKichThuoc INT PRIMARY KEY autoincrement,\n" +
                "    tenKichThuoc VARCHAR(50)\n" +
                ");";

        String SPSize = "CREATE TABLE SPSize (\n" +
                "    maSanPham INT,\n" +
                "    maDanhMuc INT,\n" +
                "    PRIMARY KEY (maSanPham, maDanhMuc),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham),\n" +
                "    FOREIGN KEY (maDanhMuc) REFERENCES DanhMuc(maDanhMuc)\n" +
                ");";


        String ChiTietDonHang = "CREATE TABLE ChiTietDonHang (\n" +
                "    maChiTietDonHang INT PRIMARY KEY autoincrement,\n" +
                "    maDonHang INT,\n" +
                "    maSanPham INT,\n" +
                "    soLuong INT,\n" +
                "    gia DECIMAL(10, 2),\n" +
                "    maKichThuoc INT,\n" +
                "    FOREIGN KEY (maDonHang) REFERENCES DonHang(maDonHang),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham),\n" +
                "    FOREIGN KEY (maKichThuoc) REFERENCES KichThuoc(maKichThuoc)\n" +
                ");";
        // Bảng người dùng : role 1 la nguoi dung , 2 la nguoi quan tri( tự thêm  );
        // public boolean dangKyTaiKhoan(NguoiDung nguoiDung) {
        //        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        //        ContentValues contentValues = new ContentValues();

        //        contentValues.put("tendangnhap", nguoiDung.getTendangnhap());
        //        contentValues.put("matkhau", nguoiDung.getMatkhau());
        //        contentValues.put("role", 1); <--------------------------------  sau này viet ham dang ky thi cho role = 1;
        //        long kq = sqLiteDatabase.insert("NGUOIDUNG", null, contentValues);
        //        return kq != -1;
        //    }

        String NguoiDung = "CREATE TABLE NguoiDung (\n" +
                "    maNguoiDung INT PRIMARY KEY autoincrement,\n" +
                "    tenNguoiDung VARCHAR(255),\n" +
                "    email VARCHAR(255),\n" +
                "    matKhau VARCHAR(255),\n" +
                "    diaChi VARCHAR(255),\n" +
                "    role INT \n" +
                ");";
        String DonHang = "CREATE TABLE DonHang (\n" +
                "    maDonHang INT PRIMARY KEY autoincrement,\n" +
                "    maNguoiDung INT,\n" +
                "    ngayDat DATE,\n" +
                "    trangThai VARCHAR(50),\n" +
                "    tongTien DECIMAL(10, 2),\n" +
                "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung)\n" +
                ");";
        String DanhGia = "CREATE TABLE DanhGia (\n" +
                "    maDanhGia INT PRIMARY KEY autoincrement,\n" +
                "    maSanPham INT,\n" +
                "    maNguoiDung INT,\n" +
                "    danhGia INT,\n" +
                "    diem DECIMAL(2, 1),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham),\n" +
                "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung)\n" +
                ");";
        String YeuThich = "CREATE TABLE YeuThich (\n" +
                "    maYeuThich INT PRIMARY KEY autoincrement,\n" +
                "    maNguoiDung INT,\n" +
                "    maSanPham INT,\n" +
                "    ngayYeuThich DATE,\n" +
                "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)\n" +
                ");";
        String LichSuSanPhamDaMua = "CREATE TABLE LichSuSanPhamDaMua (\n" +
                "    maLichSu INT PRIMARY KEY autoincrement,\n" +
                "    maNguoiDung INT,\n" +
                "    maSanPham INT,\n" +
                "    tenSanPhamDaMua VARCHAR(255) ,\n" +
                "    ngayMua DATE ,\n" +
                "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)\n" +
                ");";
        String GioHang = "CREATE TABLE GioHang (\n" +
                "    maGioHang INT PRIMARY KEY autoincrement,\n" +
                "    maDonHang INT,\n" +
                "    maSanPham INT,\n" +
                "    tongTien DECIMAL(10, 2),\n" +
                "    FOREIGN KEY (maDonHang) REFERENCES DonHang(maDonHang),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)\n" +
                ");";
        db.execSQL(DanhMuc);
        db.execSQL(SanPham);
        db.execSQL(KichThuoc);
        db.execSQL(ChiTietDonHang);
        db.execSQL(NguoiDung);
        db.execSQL(DonHang);
        db.execSQL(DanhGia);
        db.execSQL(YeuThich);
        db.execSQL(LichSuSanPhamDaMua);
        db.execSQL(GioHang);
        db.execSQL(SPSize);
        // phần thêm sản phẩm
        // danh mục
        db.execSQL("INSERT INTO DanhMuc (maDanhMuc, tenDanhMuc) VALUES (1, 'T-Shirt');");
        db.execSQL("INSERT INTO DanhMuc (maDanhMuc, tenDanhMuc) VALUES (2, 'Jacket');");
        db.execSQL("INSERT INTO DanhMuc (maDanhMuc, tenDanhMuc) VALUES (3, 'Pants');");
        // sản phẩm
        db.execSQL("INSERT INTO SanPham (maSanPham, tenSanPham, gia, moTa, maDanhMuc, soLuong) VALUES (1, 'Áo thun thêu chữ ', 699.99, 'Mô tả sản phẩm 1', 1, 50);");
        db.execSQL("INSERT INTO SanPham (maSanPham, tenSanPham, gia, moTa, maDanhMuc, soLuong) VALUES (2, 'Áo khoác', 19.99, 'Mô tả sản phẩm 2 ', 2, 200);");
        db.execSQL("INSERT INTO SanPham (maSanPham, tenSanPham, gia, moTa, maDanhMuc, soLuong) VALUES (3, 'Quần jean rách', 29.99, 'Mô tả sản phẩm 3', 3, 30);");
        // người dùng
        db.execSQL("INSERT INTO NguoiDung (maNguoiDung, tenNguoiDung, email, matKhau, diaChi, role) VALUES (1, 'John Doe', 'john@example.com', '123', '123 Main St', 1);");
        db.execSQL("INSERT INTO NguoiDung (maNguoiDung, tenNguoiDung, email, matKhau, diaChi, role) VALUES (2, 'admin', 'jane@example.com', 'admin', '456 Elm St', 2);");
        // kích thước (Không thêm)
        db.execSQL("INSERT INTO KichThuoc (maKichThuoc, tenKichThuoc) VALUES (1, 'M');");
        db.execSQL("INSERT INTO KichThuoc (maKichThuoc, tenKichThuoc) VALUES (2, 'L');");
        db.execSQL("INSERT INTO KichThuoc (maKichThuoc, tenKichThuoc) VALUES (3, 'XL');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DanhMuc");
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        db.execSQL("DROP TABLE IF EXISTS KichThuoc");
        db.execSQL("DROP TABLE IF EXISTS ChiTietDonHang");
        db.execSQL("DROP TABLE IF EXISTS NguoiDung");
        db.execSQL("DROP TABLE IF EXISTS DonHang");
        db.execSQL("DROP TABLE IF EXISTS DanhGia");
        db.execSQL("DROP TABLE IF EXISTS YeuThich");
        db.execSQL("DROP TABLE IF EXISTS LichSuSanPhamDaMua");
        db.execSQL("DROP TABLE IF EXISTS GioHang");
        db.execSQL("DROP TABLE IF EXISTS SPSize");
        onCreate(db);
    }
}
