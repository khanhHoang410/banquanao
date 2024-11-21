package com.example.duan1.Adapter;

import android.content.Context;
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
        holder.tvNameSanPham.setText(sanPham.getTenSanPham());
        holder.tvGiaSanPham.setText(String.valueOf(sanPham.getGia()));
        holder.anhSanPham.setImageBitmap(sanPham.getAnh());
        // Cập nhật ảnh yêu thích nếu cần// đéo cần đâu
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
}