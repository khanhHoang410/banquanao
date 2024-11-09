package com.example.duan1.Database;

public class Data_SQLite {

    public static final String INSERT_SANPHAM = "INSERT INTO SanPham (tenSanPham, gia, moTa, anh, maDanhMuc) VALUES\n" +
            "('Hoodie Basic', 300000, 'Hoodie màu đen cơ bản', 'anh_hoodie.jpg', 1),\n" +
            "        ('Hoodie Crop', 350000, 'Hoodie crop dành cho nữ', 'anh_hoodie_crop.jpg', 1),\n" +
            "        ('T-Shirt Graphic', 150000, 'Áo thun với họa tiết', 'anh_tshirt.jpg', 2),\n" +
            "        ('T-Shirt Plain', 100000, 'Áo thun trơn đơn giản', 'anh_tshirt_plain.jpg', 2),\n" +
            "        ('Quần Jean Slim', 400000, 'Quần jeans dáng slim', 'anh_jeans_slim.jpg', 3),\n" +
            "        ('Quần Short Jeans', 250000, 'Quần short jeans thoải mái', 'anh_jeans_short.jpg', 3),\n" +
            "        ('Áo Khoác Bomber', 500000, 'Áo khoác bomber phong cách', 'anh_bomber.jpg', 4),\n" +
            "        ('Áo Khoác Jeans', 550000, 'Áo khoác jeans mạnh mẽ', 'anh_jeans_jacket.jpg', 4),\n" +
            "        ('Váy Midi', 450000, 'Váy midi công sở', 'anh_vay_midi.jpg', 5),\n" +
            "        ('Váy Dài', 600000, 'Váy dài cho dạ tiệc', 'anh_vay_dai.jpg', 5),\n" +
            "        ('Giày Sneaker', 700000, 'Giày sneaker thể thao', 'anh_sneaker.jpg', 6),\n" +
            "        ('Giày Boots', 800000, 'Giày boots da thời trang', 'anh_boots.jpg', 6);";
    public static final String INSERT_GIOHANG = " INSERT INTO GioHang (maNguoiDung, maSanPham, soLuong) VALUES\n" +
            "(1, 1, 2),\n" +
            "        (1, 2, 1),\n" +
            "        (2, 3, 2),\n" +
            "        (2, 4, 1),\n" +
            "        (3, 5, 3),\n" +
            "        (3, 6, 1),\n" +
            "        (4, 7, 1),\n" +
            "        (4, 8, 2),\n" +
            "        (5, 9, 2),\n" +
            "        (5, 10, 1),\n" +
            "        (6, 11, 1),\n" +
            "        (6, 12, 2);\n";

    public static final String INSERT_DONHANG =" INSERT INTO DonHang (maNguoiDung, ngayDat, tongTien, trangThai) VALUES\n" +
            "(1, '2024-11-01', 800000, 'Đã giao'),\n" +
            "        (2, '2024-11-02', 600000, 'Đang xử lý'),\n" +
            "        (3, '2024-11-05', 1000000, 'Đã giao'),\n" +
            "        (4, '2024-11-07', 1500000, 'Đang xử lý'),\n" +
            "        (5, '2024-11-09', 1100000, 'Đã giao'),\n" +
            "        (6, '2024-11-10', 1600000, 'Đang xử lý');";

    public static final String INSERT_CHITIETDONHANG = "INSERT INTO ChiTietDonHang (maDonHang, maSanPham, soLuong, gia) VALUES\n" +
            "(1, 1, 2, 300000),\n" +
            "        (1, 2, 1, 150000),\n" +
            "        (2, 3, 2, 150000),\n" +
            "        (2, 4, 1, 100000),\n" +
            "        (3, 5, 3, 400000),\n" +
            "        (3, 6, 1, 250000),\n" +
            "        (4, 7, 1, 500000),\n" +
            "        (4, 8, 2, 550000),\n" +
            "        (5, 9, 2, 450000),\n" +
            "        (5, 10, 1, 600000),\n" +
            "        (6, 11, 1, 700000),\n" +
            "        (6, 12, 2, 800000);";

    public static final String INSERT_DANHGIA = "\n" +
            "    INSERT INTO DanhGia (maChiTietDonHang, DanhGia) VALUES\n" +
            "(1, 'Hoodie rất đẹp và thoải mái, tôi rất hài lòng!'),\n" +
            "        (2, 'Áo thun đẹp, màu sắc chuẩn, giao hàng nhanh chóng'),\n" +
            "        (3, 'Quần jean dáng đẹp nhưng hơi chật với tôi'),\n" +
            "        (4, 'Áo khoác rất đẹp, phù hợp với phong cách của tôi'),\n" +
            "        (5, 'Váy đẹp, đúng như mô tả, thoải mái khi mặc'),\n" +
            "        (6, 'Giày cực chất, tôi rất thích'),\n" +
            "        (7, 'Áo khoác rất bền, chất liệu tốt, đáng tiền'),\n" +
            "        (8, 'Giày boots đẹp nhưng hơi cứng'),\n" +
            "        (9, 'Áo hoodie rất ấm và dễ phối đồ'),\n" +
            "        (10, 'Váy dạ tiệc sang trọng, tôi rất ưng ý'),\n" +
            "        (11, 'Giày sneaker tuyệt vời, rất êm khi đi'),\n" +
            "        (12, 'Giày boots đẹp nhưng hơi đắt');";


    public static final String INSERT_DANHMUC = "INSERT INTO DanhMuc (tenDanhMuc) VALUES\n" +
            "('Hoodies'),\n" +
            "('T-Shirts'),\n" +
            "('Bottoms'),\n" +
            "('Outerwear'),\n" +
            "('Dresses'),\n" +
            "('Shoes');";


}
