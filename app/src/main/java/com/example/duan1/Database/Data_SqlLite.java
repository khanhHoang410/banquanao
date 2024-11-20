package com.example.duan1.Database;

public class Data_SqlLite {
    public static final String INSERT_DANH_MUC = "INSERT INTO DanhMuc(maDanhMuc, tenDanhMuc) VALUES\n" +
            "(1, 'tees'),\n" +
            "(2, 'shorts'),\n" +
            "(3, 'tank'),\n" +
            "(4, 'hoodies');";
    public static final String INSERT_KICH_THUOC = "INSERT INTO KichThuoc(maKichThuoc, tenKichThuoc) VALUES\n" +
            "(1, 'S'),\n" +
            "(2, 'M'),\n" +
            "(3, 'L'),\n" +
            "(4, 'XL');";
    public static final String INSERT_NGUOI_DUNG = "INSERT INTO NguoiDung(maNguoiDung, tenNguoiDung, email, matKhau, role) VALUES\n" +
            "(1, 'Nguyen Van A', 'a@gmail.com', '123456', 1),\n" +
            "(2, 'Tran Thi B', 'b@gmail.com', '123456', 1),\n" +
            "(3, 'Admin', 'admin@gmail.com', 'admin', 2);";
    public static final String INSERT_SAN_PHAM = "INSERT INTO SanPham(maSanPham, tenSanPham, gia, moTa, maDanhMuc, soLuong, anh) VALUES\n" +
            "(1, 'Áo thun trắng', 150000, 'Áo thun chất liệu cotton', 1, 50, 'ao_trang.jpg'),\n" +
            "(2, 'Quần shorts đen', 200000, 'Quần shorts chất liệu vải dù', 2, 30, 'quan_shorts_den.jpg'),\n" +
            "(3, 'Tank top thể thao', 180000, 'Áo tank top thoáng khí', 3, 20, 'tank_top.jpg'),\n" +
            "(4, 'Hoodie xám', 350000, 'Áo hoodie ấm áp', 4, 15, 'hoodie_xam.jpg'),\n" +
            "(5, 'Áo thun xanh', 160000, 'Áo thun cotton màu xanh', 1, 45, 'ao_xanh.jpg'),\n" +
            "(6, 'Quần shorts thể thao', 220000, 'Quần shorts thoáng khí', 2, 25, 'quan_shorts_the_thao.jpg'),\n" +
            "(7, 'Tank top gym', 200000, 'Tank top dành cho phòng gym', 3, 18, 'tank_gym.jpg'),\n" +
            "(8, 'Hoodie đen', 400000, 'Áo hoodie phong cách', 4, 10, 'hoodie_den.jpg'),\n" +
            "(9, 'Áo thun đỏ', 170000, 'Áo thun màu đỏ nổi bật', 1, 40, 'ao_do.jpg'),\n" +
            "(10, 'Quần shorts jean', 250000, 'Quần shorts chất liệu jean', 2, 20, 'quan_shorts_jean.jpg'),\n" +
            "(11, 'Tank top năng động', 190000, 'Áo tank top kiểu dáng năng động', 3, 22, 'tank_nang_dong.jpg'),\n" +
            "(12, 'Hoodie basic', 300000, 'Hoodie kiểu dáng cơ bản', 4, 12, 'hoodie_basic.jpg'),\n" +
            "(13, 'Áo thun họa tiết', 180000, 'Áo thun họa tiết độc đáo', 1, 35, 'ao_hoa_tiet.jpg'),\n" +
            "(14, 'Quần shorts kaki', 240000, 'Quần shorts chất liệu kaki', 2, 28, 'quan_shorts_kaki.jpg'),\n" +
            "(15, 'Tank top dáng dài', 210000, 'Tank top dáng dài thời trang', 3, 17, 'tank_dang_dai.jpg'),\n" +
            "(16, 'Hoodie màu pastel', 450000, 'Hoodie màu pastel trẻ trung', 4, 8, 'hoodie_pastel.jpg'),\n" +
            "(17, 'Áo thun đen', 190000, 'Áo thun cotton màu đen', 1, 48, 'ao_den.jpg'),\n" +
            "(18, 'Quần shorts thể thao màu xám', 230000, 'Quần shorts thể thao thời trang', 2, 30, 'quan_shorts_xam.jpg'),\n" +
            "(19, 'Tank top mùa hè', 170000, 'Áo tank top thoáng mát mùa hè', 3, 23, 'tank_mua_he.jpg'),\n" +
            "(20, 'Hoodie họa tiết', 500000, 'Hoodie với họa tiết nổi bật', 4, 7, 'hoodie_hoa_tiet.jpg');";

}
