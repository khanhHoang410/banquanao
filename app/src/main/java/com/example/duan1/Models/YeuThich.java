package com.example.duan1.Models;

import java.util.Date;

public class YeuThich {
    private int maYeuThich; // PRIMARY KEY
    private int maNguoiDung; // INT
    private int maSanPham; // INT
    private Date ngayYeuThich; // DATE
}
//  String inputDate = "2024-11-13"; // Ngày đầu vào dưới dạng chuỗi
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//
//            Date ngayDat = dateFormat.parse(inputDate); // Chuyển đổi chuỗi thành Date
//            System.out.println("Ngày đặt: " + ngayDat);