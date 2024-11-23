package com.example.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "FaciwayDB";
    private static final int DB_VERSION= 12;

    // Bảng danh mục


    public DbHelper( Context context){
        super(context, DB_Name,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String DanhMuc = "CREATE TABLE DanhMuc (\n" +
                "    maDanhMuc INTEGER PRIMARY KEY autoincrement,\n" +
                "    tenDanhMuc VARCHAR(255)\n" +
                ");";

        String SanPham = "CREATE TABLE SanPham (\n" +
                "    maSanPham INTEGER  PRIMARY KEY autoincrement,\n" +
                "    tenSanPham VARCHAR(255),\n" +
                "    gia DECIMAL(10, 2),\n" +
                "    moTa TEXT,\n" +
                "    maDanhMuc INTEGER,\n" +
                "    soLuong INTEGER,\n" +
                "    anh BLOB,\n" +
                "    isYeuThich INTEGER DEFAULT 0,\n" +
                "    FOREIGN KEY (maDanhMuc) REFERENCES DanhMuc(maDanhMuc)\n" +
                ");";
        String KichThuoc = "CREATE TABLE KichThuoc (\n" +
                "    maKichThuoc INTEGER  PRIMARY KEY autoincrement,\n" +
                "    tenKichThuoc VARCHAR(50)\n" +
                ");";

        String SPSize = "CREATE TABLE SPSize (\n" +
                "    maSanPham INTEGER ,\n" +
                "    maDanhMuc INTEGER ,\n" +
                "    PRIMARY KEY (maSanPham, maDanhMuc),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham),\n" +
                "    FOREIGN KEY (maDanhMuc) REFERENCES DanhMuc(maDanhMuc)\n" +
                ");";


        String ChiTietDonHang = "CREATE TABLE ChiTietDonHang (\n" +
                "    maChiTietDonHang INTEGER  PRIMARY KEY autoincrement,\n" +
                "    maDonHang INTEGER ,\n" +
                "    maSanPham INTEGER ,\n" +
                "    soLuong INTEGER ,\n" +
                "    gia DECIMAL(10, 2),\n" +
                "    maKichThuoc INT,\n" +
                "    FOREIGN KEY (maDonHang) REFERENCES DonHang(maDonHang),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham),\n" +
                "    FOREIGN KEY (maKichThuoc) REFERENCES KichThuoc(maKichThuoc)\n" +
                ");";
        String NguoiDung = "CREATE TABLE NguoiDung (\n" +
                "    maNguoiDung INTEGER  PRIMARY KEY autoincrement,\n" +
                "    tenNguoiDung VARCHAR(255),\n" +
                "    email VARCHAR(255),\n" +
                "    matKhau VARCHAR(255),\n" +
                "    diaChi VARCHAR(255),\n" +
                "    sdt INTEGER ,\n" +
                "    role INTEGER \n" +
                ");";
        String DonHang = "CREATE TABLE DonHang (\n" +
                "    maDonHang INTEGER  PRIMARY KEY autoincrement,\n" +
                "    maNguoiDung INTEGER ,\n" +
                "    ngayDat DATE,\n" +
                "    trangThai VARCHAR(50),\n" +
                "    tongTien DECIMAL(10, 2),\n" +
                "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung)\n" +
                ");";
        String DanhGia = "CREATE TABLE DanhGia (\n" +
                "    maDanhGia INTEGER  PRIMARY KEY autoincrement,\n" +
                "    maSanPham INTEGER ,\n" +
                "    maNguoiDung INTEGER ,\n" +
                "    danhGia INTEGER ,\n" +
                "    diem DECIMAL(2, 1),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham),\n" +
                "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung)\n" +
                ");";
        String YeuThich = "CREATE TABLE YeuThich (\n" +
                "    maYeuThich INTEGER  PRIMARY KEY autoincrement,\n" +
                "    maNguoiDung INTEGER ,\n" +
                "    maSanPham INTEGER ,\n" +
                "    ngayYeuThich DATE,\n" +
                "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)\n" +
                ");";
        String LichSuSanPhamDaMua = "CREATE TABLE LichSuSanPhamDaMua (\n" +
                "    maLichSu INTEGER  PRIMARY KEY autoincrement,\n" +
                "    maNguoiDung INTEGER ,\n" +
                "    maSanPham INTEGER ,\n" +
                "    tenSanPhamDaMua VARCHAR(255) ,\n" +
                "    ngayMua DATE ,\n" +
                "    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung),\n" +
                "    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)\n" +
                ");";
        String GioHang = "CREATE TABLE GioHang (\n" +
                "    maGioHang INTEGER  PRIMARY KEY autoincrement,\n" +
                "    maDonHang INTEGER ,\n" +
                "    maSanPham INTEGER ,\n" +
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
        db.execSQL(Data_SqlLite.INSERT_DANH_MUC);
        db.execSQL(Data_SqlLite.INSERT_KICH_THUOC);
        db.execSQL(Data_SqlLite.INSERT_SAN_PHAM);
        // phần thêm sản phẩm
        // danh mục
        // sản phẩm
//        db.execSQL("INSERT INTO SanPham (maSanPham, tenSanPham, gia, moTa, maDanhMuc, soLuong) VALUES (1, 'Áo thun thêu chữ ', 699.99, 'Mô tả sản phẩm 1', 1, 50);");
//        db.execSQL("INSERT INTO SanPham (maSanPham, tenSanPham, gia, moTa, maDanhMuc, soLuong) VALUES (2, 'Áo khoác', 19.99, 'Mô tả sản phẩm 2 ', 2, 200);");
//        db.execSQL("INSERT INTO SanPham (maSanPham, tenSanPham, gia, moTa, maDanhMuc, soLuong) VALUES (3, 'Quần jean rách', 29.99, 'Mô tả sản phẩm 3', 3, 30);");
        // người dùng
        db.execSQL("INSERT INTO NguoiDung (maNguoiDung, tenNguoiDung, email, matKhau, diaChi,sdt, role) VALUES (1, 'John Doe', 'namvu@gmail.com', '123456', '123 Main St',099999999, 1);");
        db.execSQL("INSERT INTO NguoiDung (maNguoiDung, tenNguoiDung, email, matKhau, diaChi,sdt, role) VALUES (2, 'admin', 'admin@gmail.com', '123456', '456 Elm St',099999999, 2);");
        // kích thước (Không thêm)

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion){
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
}
