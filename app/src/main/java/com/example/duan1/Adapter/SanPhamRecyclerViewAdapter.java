package com.example.duan1.Adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.BlankFragment;
import com.example.duan1.ChitietSanPham;
import com.example.duan1.Dao.SanPhamDAO;
import com.example.duan1.MainActivity;
import com.example.duan1.Models.SanPham;
import com.example.duan1.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamRecyclerViewAdapter extends RecyclerView.Adapter<SanPhamRecyclerViewAdapter.ViewHolder> {

    private Context context;
    SanPhamDAO sanPhamDAO;
    private List<SanPham> list;
    public OnYeuThichChangeListener listener;
    private static View.OnLongClickListener onItemLongClickListener;
    public interface OnYeuThichChangeListener {
        void onYeuThichChange(SanPham sanPham);
    }
    public SanPhamRecyclerViewAdapter(Context context, List<SanPham> list,OnYeuThichChangeListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }
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

//        holder.anhSanPham.setImageBitmap(sanPham.getAnh());
        // Lấy ảnh từ drawable dựa trên tên ảnh


        File imageFile = new File(sanPham.getAnh());
        if (imageFile.exists()){
            holder.anhSanPham.setImageURI(Uri.fromFile(imageFile));
        }else {
            // xử lý lỗi nếu không tìm thấy ảnh trong bọ nhớ cache
            holder.anhSanPham.setImageResource(R.drawable.hoodieden2);
        }

        Bitmap bitmap = getBitmapFromDrawable(sanPham.getAnh(), context);
        if (bitmap != null) {

            holder.anhSanPham.setImageBitmap(bitmap);
        }
//        holder.itemView.setOnTouchListener(((v, event) -> {
//            switch (event.getActionMasked()){
//                case MotionEvent.ACTION_HOVER_ENTER:
//                    holder.btnAddtoCart.setVisibility(View.VISIBLE);
//                    return true;
//                case MotionEvent.ACTION_HOVER_EXIT:
//                    holder.btnAddtoCart.setVisibility(View.GONE);
//                    return true;
//            }
//            return false;
//
//        }));

      
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

//        holder.btnAddtoCart.setOnClickListener(v->{
//
//        });
        // Hiển thị trạng thái yêu thích
        holder.anhYeuThich.setImageResource(sanPham.getYeuThich() ? R.drawable.ic_redlove : R.drawable.love_icon);
        // Xử lý click vào icon yêu thích
        holder.anhYeuThich.setOnClickListener(v->{
            boolean newStatus = !sanPham.getYeuThich(); // đảo trạng thái yêu thích
            sanPham.setYeuThich(newStatus);
            // thay đổi màu icon trái tim
            holder.anhYeuThich.setImageResource(newStatus ? R.drawable.ic_redlove: R.drawable.love_icon);
            // Hiển thị Toast message
            if (newStatus) {
                Toast.makeText(context, "Bạn vừa thêm 1 sản phẩm yêu thích", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Bạn vừa xóa 1 sản phẩm yêu thích", Toast.LENGTH_SHORT).show();
            }


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
        Button btnAddtoCart;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNameSanPham = itemView.findViewById(R.id.product_name);
            tvGiaSanPham = itemView.findViewById(R.id.product_price);
            anhSanPham = itemView.findViewById(R.id.product_iamge);
            anhYeuThich = itemView.findViewById(R.id.icon_favorite);
//            btnAddtoCart = itemView.findViewById(R.id.btnAddtoCart);

            itemView.setOnLongClickListener(v -> {
                if (onItemLongClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        return onItemLongClickListener.onLongClick(v); // Gọi onLongClick()
                    }
                }
                return false;
            });

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
    public void setOnItemLongClickListener(View.OnLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }
    private void showDialogSuaSanPham(SanPham sanPham){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sửa sản phẩm");

        // tạo layout cho dialog
        View view = LayoutInflater.from(context).inflate(R.layout.suasanpham,null);
        builder.setView(view);
        // lấy các view từ layout
        EditText etTenSanPham = view.findViewById(R.id.etTenSanPham);
        EditText etGiaSanPham = view.findViewById(R.id.etGiaSanPham);
        EditText etMoTaSanPham = view.findViewById(R.id.etMoTaSanPham);
        ImageView imgSanPham = view.findViewById(R.id.imgSanPham);

        // hiển thị thông tin của sản phẩm hiện tại
        etTenSanPham.setText(sanPham.getTenSanPham());
        etGiaSanPham.setText(String.valueOf(sanPham.getGia()));
        etMoTaSanPham.setText(sanPham.getMoTa());

        // load ảnh sản phẩm vào imgsanpham

        // xử lý sự kiện click vào nút sửa
        builder.setPositiveButton("Sửa",((dialog, which) -> {
            // lấy thông tin sản phẩm đã sửa
            String tenSanPhamMoi = etTenSanPham.getText().toString();
            Float giaSanPhamMoi = Float.parseFloat(etGiaSanPham.getText().toString());
            String moTaSanPhamMoi = etMoTaSanPham.getText().toString();

            // lấy đường dẫn ảnh mới
            // cập nhật thông tin sản phẩm
            sanPham.setTenSanPham(tenSanPhamMoi);
            sanPham.setGia(giaSanPhamMoi);
            sanPham.setMoTa(moTaSanPhamMoi);
            // câp nhật đường dẫn ảnh mới

            sanPhamDAO.update(sanPham);
            notifyDataSetChanged();

        }));

    }

}