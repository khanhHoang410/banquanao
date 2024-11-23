package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.BlankFragment;
import com.example.duan1.ChitietSanPham;
import com.example.duan1.MainActivity;
import com.example.duan1.Models.SanPham;
import com.example.duan1.R;

import java.util.List;

public class SanPhamRecyclerViewAdapter extends RecyclerView.Adapter<SanPhamRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<SanPham> list;
    public OnYeuThichChangeListener listener;
    public interface OnYeuThichChangeListener {
        void onYeuThichChange(SanPham sanPham);
    }
    public SanPhamRecyclerViewAdapter(Context context, List<SanPham> list,OnYeuThichChangeListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
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
//        holder.anhSanPham.setImageBitmap(sanPham.getAnh());
        // Lấy ảnh từ drawable dựa trên tên ảnh

        Bitmap bitmap = getBitmapFromDrawable(sanPham.getAnh(), context);
        if (bitmap != null) {
            holder.anhSanPham.setImageBitmap(bitmap);
        }
        // xử lý sự kiện click vào item
        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(context, ChitietSanPham.class);
            Log.d("ChitietSanPham", "Tên sản phẩm: " + sanPham.getTenSanPham());
            Log.d("ChitietSanPham", "Mô tả sản phẩm: " + sanPham.getMoTa());
            intent.putExtra("tenSanPham", sanPham.getTenSanPham());
            intent.putExtra("gia", String.valueOf(sanPham.getGia()));
            intent.putExtra("anh", sanPham.getAnh());
            intent.putExtra("moTa", sanPham.getMoTa());
            context.startActivity(intent);
        });
        // Hiển thị trạng thái yêu thích
        holder.anhYeuThich.setImageResource(sanPham.getYeuThich() ? R.drawable.love_icon : R.drawable.ic_launcher_background);
        // Xử lý click vào icon yêu thích
        holder.anhYeuThich.setOnClickListener(v->{
            boolean newStatus = !sanPham.getYeuThich(); // đảo trạng thái yêu thích
            sanPham.setYeuThich(newStatus);
            // thay đổi màu icon trái tim
            holder.anhYeuThich.setImageResource(newStatus ? R.drawable.love_icon: R.drawable.ic_launcher_background);
            // lưu danh sách yêu thích
           if (listener!=null){
               listener.onYeuThichChange(sanPham);
           }
        });
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
    // Hàm lấy Bitmap từ drawable
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