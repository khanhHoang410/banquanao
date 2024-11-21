package com.example.duan1.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Models.SanPham;
import com.example.duan1.R;

import java.util.List;

public class SanPhamRecyclerViewAdapter extends RecyclerView.Adapter<SanPhamRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<SanPham> list;

    public SanPhamRecyclerViewAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham = list.get(position);
//        String fileName = sanPham.getAnh().split("\\.")[0];
//        int resourceId = context.getResources().getIdentifier(sanPham.getAnh().toString(),"drawable",context.getPackageName());
//        holder.anhSanPham.setImageResource(resourceId);
        holder.tvNameSanPham.setText(sanPham.getTenSanPham());
        holder.tvGiaSanPham.setText(String.valueOf(sanPham.getGia()));

//        holder.anhSanPham.setImageBitmap(sanPham.getAnh());
//        Lấy ảnh từ drawable dựa trên tên ảnh
        Bitmap bitmap = getBitmapFromDrawable(sanPham.getAnh(), context);
        if (bitmap != null) {
            holder.anhSanPham.setImageBitmap(bitmap);
        }

        // Cập nhật ảnh yêu thích nếu cần

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameSanPham, tvGiaSanPham;
        ImageView anhSanPham, anhYeuThich;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNameSanPham = itemView.findViewById(R.id.product_name);
            tvGiaSanPham = itemView.findViewById(R.id.product_price);
            anhSanPham = itemView.findViewById(R.id.product_iamge);
            anhYeuThich = itemView.findViewById(R.id.icon_favorite);

        }
    }
    public void updateData(List<SanPham> newList) {
        this.list = newList;  // Cập nhật lại danh sách dữ liệu
        notifyDataSetChanged();  // Thông báo cập nhật dữ liệu
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