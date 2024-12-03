package com.example.duan1.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Dao.GioHangDAO;
import com.example.duan1.Dao.SanPhamDAO;
import com.example.duan1.Models.GioHang;
import com.example.duan1.Models.SanPham;
import com.example.duan1.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private int userId;
    private Context context;
    List<GioHang> gioHangList;
    private GioHangDAO gioHangDAO;
    private SanPhamDAO sanPhamDAO;
    private TextView totalPriceTextView; // TextView để hiển thị tổng tiền
    private int currentQuantity = 1;
    public CartAdapter(Context context, List<GioHang> gioHangList, TextView totalPriceTextView,int userId) {
        this.context = context;
        this.gioHangList = gioHangList;
        this.totalPriceTextView = totalPriceTextView;
        this.userId = userId;
        gioHangDAO = new GioHangDAO(context);
        sanPhamDAO = new SanPhamDAO(context);

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        Log.d("CartAdapter", "gioHang1: " + gioHang);
        if (gioHang != null) {
            // lấy mã sản phẩm từ giỏ hàng
            int maSanPham = gioHang.getMaSanPham();
            Log.d("CartAdapter", "maSanPham int: " + maSanPham);
//            String maSanPhamString = String.valueOf(maSanPham);
//            Log.d("CartAdapter", "maSanPham: " + maSanPhamString);
            // lấy sản phẩm từ masanpham
            SanPham sanPham = sanPhamDAO.getID(maSanPham);
            if (sanPham != null) {
                // Hiển thị dữ liệu sản phẩm
//                holder.productImage.setImageResource(sanPham.getAnh());
                Bitmap bitmap = getBitmapFromDrawable(sanPham.getAnh(), context);
                if (bitmap != null) {
                    holder.productImage.setImageBitmap(bitmap);
                }

                holder.productName.setText(sanPham.getTenSanPham());
                //holder.colorSize.setText(sanPham.getColor() + " | Size: " + sanPham.getSize()); // Thay thế bằng cách lấy màu sắc và kích thước từ sanPham
                holder.price.setText("$" + sanPham.getGia()); // Hiển thị giá sản phẩm
//                holder.quantity.setText(String.valueOf(sanPham.getSoLuong())); // Hiển thị số lượng mặc định là 1
//                holder.quantity.setTag(sanPham.getSoLuong()); // Lưu trữ số lượng sản phẩm hiện tại vào tag
//                holder.quantity.setText(String.valueOf(sanPham.getSoLuong())); // Hiển thị số lượng sản phẩm hiện tại
                holder.quantity.setText(String.valueOf(currentQuantity));


                holder.increaseButton.setOnClickListener(v -> {
                    currentQuantity++; // Tăng số lượng sản phẩm hiện tại
                    holder.quantity.setText(String.valueOf(currentQuantity));

                    sanPham.setSoLuong(sanPham.getSoLuong()+1);
                    sanPhamDAO.update(sanPham);
                    // Cập nhật tổng tiền trong GioHang
                    gioHang.setTongTien(sanPham.getGia() * currentQuantity);
                    gioHangDAO.update(gioHang);

                    // Cập nhật tổng tiền
                    updateTotalPrice();
                    notifyItemChanged(position);
                });

                holder.decreaseButton.setOnClickListener(v -> {
                    if (currentQuantity > 1) {
                        currentQuantity--;
                        holder.quantity.setText(String.valueOf(currentQuantity));

                        // Cập nhật tổng tiền trong GioHang
                        sanPham.setSoLuong(currentQuantity);
                        sanPhamDAO.update(sanPham);

                        gioHang.setTongTien(sanPham.getGia() * currentQuantity);
                        gioHangDAO.update(gioHang);

                        // Cập nhật tổng tiền
                        updateTotalPrice();
                        notifyItemChanged(position);
                    }
                });

            } else {
                // Xử lý trường hợp không tìm thấy sản phẩm
            }
        } else {
            // Xử lý trường hợp gioHang là null
            Log.d("CartAdapter", "gioHang là null");
        }
        holder.imgXoa.setOnClickListener(v->{
            if (gioHang!=null){
                gioHangDAO.delete(gioHang.getMaGioHang());
                gioHangList.remove(gioHang);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,gioHangList.size());
                updateTotalPrice();
            }

        });
    }


    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, increaseButton, decreaseButton,imgXoa;
        TextView productName, colorSize, price;
        EditText quantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
//            colorSize = itemView.findViewById(R.id.color_size);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            increaseButton = itemView.findViewById(R.id.increase_button);
            decreaseButton = itemView.findViewById(R.id.decrease_button);
            imgXoa = itemView.findViewById(R.id.imgXoa);
        }
    }


    // Phương thức để cập nhật tổng tiền
    public void updateTotalPrice() {
        double totalPrice = 0;
        for (GioHang gioHang : gioHangList) {
            totalPrice += gioHang.getTongTien();
        }
        totalPriceTextView.setText("Total Price: $" + totalPrice);
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
    public void updateData(List<GioHang> newGioHangList) {

        this.gioHangList = newGioHangList;
        notifyDataSetChanged();
    }

}
