package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.ChitietSanPham;
import com.example.duan1.Models.SanPham;
import com.example.duan1.R;

import java.io.File;
import java.util.ArrayList;

public class HotSaleAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham>list;

    public HotSaleAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_san_pham_2, null);
        SanPham sanPham = list.get(position);
        TextView tvNameSanPham, tvGiaSanPham,soluong;
        ImageView anhSanPham;
        tvNameSanPham = convertView.findViewById(R.id.product_name);
        tvGiaSanPham = convertView.findViewById(R.id.product_price);
        anhSanPham = convertView.findViewById(R.id.product_iamge);
        soluong = convertView.findViewById(R.id.soluong);

        tvNameSanPham.setText(sanPham.getTenSanPham());
        tvGiaSanPham.setText(String.valueOf(sanPham.getGia()));
//        holder.anhSanPham.setImageBitmap(sanPham.getAnh());
        // Lấy ảnh từ drawable dựa trên tên ảnh

        File imageFile = new File(sanPham.getAnh());
        if (imageFile.exists()){
            anhSanPham.setImageURI(Uri.fromFile(imageFile));
        }else {
            // xử lý lỗi nếu không tìm thấy ảnh trong bọ nhớ cache
           anhSanPham.setImageResource(R.drawable.hoodieden2);
        }

        Bitmap bitmap = getBitmapFromDrawable(sanPham.getAnh(), context);
        if (bitmap != null) {
            anhSanPham.setImageBitmap(bitmap);
        }
        soluong.setText("Số lượng : "+sanPham.getSoLuong());

        // xử lý sự kiện click vào item
        convertView.setOnClickListener(v->{
            Intent intent = new Intent(context, ChitietSanPham.class);
            Log.d("ChitietSanPham", "Tên sản phẩm: " + sanPham.getTenSanPham());
            Log.d("ChitietSanPham", "Mô tả sản phẩm: " + sanPham.getMoTa());
            intent.putExtra("tenSanPham", sanPham.getTenSanPham());
            intent.putExtra("gia", String.valueOf(sanPham.getGia()));
            intent.putExtra("anh", sanPham.getAnh());
            intent.putExtra("moTa", sanPham.getMoTa());
            context.startActivity(intent);
        });

        return convertView;
    }
    private Bitmap getBitmapFromDrawable(String fileName, Context context) {
        // Lấy resource ID từ tên file
        int resourceId = context.getResources().getIdentifier(fileName.replace(".webp", ""), "drawable", context.getPackageName());
        if (resourceId != 0) {
            // Chuyển đổi resource ID thành Bitmap
            return BitmapFactory.decodeResource(context.getResources(), resourceId);
        }
        return null; // Trả về null nếu không tìm thấy ảnh
    }
}
