package com.example.duan1.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Dao.DonHangDAO;
import com.example.duan1.Models.DonHang;
import com.example.duan1.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder> {

    private Context context;
    private List<DonHang> donHangList;

    public DonHangAdapter(Context context, List<DonHang> donHangList) {
        this.context = context;
        this.donHangList = donHangList;
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donhang, parent, false);
        return new DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {
        DonHang donHang = donHangList.get(position);

        holder.tvTenKhachHang.setText(donHang.getTen()); // Tên khách hàng
        // Định dạng ngày đặt hàng
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String ngayDatFormatted = dateFormat.format(donHang.getNgayDat());
        holder.tvNgayDat.setText("Ngày đặt: " + ngayDatFormatted);
        holder.tvTrangThai.setText("Trạng thái: " + donHang.getTrangThai());
        holder.tvTongTien.setText("Tổng tiền: " + donHang.getTongTien() + " VND");
        holder.tvSoDienThoai.setText("Số điện thoại: " + donHang.getPhoneNumber());
        holder.tvDiaChi.setText("Địa chỉ: " + donHang.getDiaChi());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa dữ liệu");
                builder.setMessage("Bạn có chắc muốn xóa không ?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DonHangDAO donHangDAO = new DonHangDAO(context);
                        donHangDAO.delete(donHang.getMaDonHang());
                        donHangList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, donHangList.size()); // Cập nhật lại vị trí
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenKhachHang, tvNgayDat, tvTrangThai, tvTongTien, tvSoDienThoai, tvDiaChi;

        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenKhachHang = itemView.findViewById(R.id.tvTenKhachHang);
            tvNgayDat = itemView.findViewById(R.id.tvNgayDat);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);
            tvSoDienThoai = itemView.findViewById(R.id.tvSoDienThoai);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
        }
    }
}