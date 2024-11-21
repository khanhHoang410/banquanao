package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.duan1.BlankFragment;
import com.example.duan1.Dao.DanhMucDAO;
import com.example.duan1.Dao.SanPhamDAO;
import com.example.duan1.Models.DanhMuc;
import com.example.duan1.Models.SanPham;
import com.example.duan1.R;

import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {

    Context context;
    BlankFragment fragsanpham;
    List<SanPham> list;
    TextView tvgiaSanPham, tvNameSanPham;
    ImageView anhSanpham,anhyeuthich;

    public SanPhamAdapter(@NonNull Context context,BlankFragment fragsanpham,List<SanPham> list ) {
        super(context, 0,list);
        this.context =context;
        this.fragsanpham = fragsanpham;
        this.list =list;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=  inflater.inflate(R.layout.item_san_pham,null);
        }
        final SanPham item = list.get(position);
        if (item!=null){
           DanhMucDAO danhMucDAO = new DanhMucDAO(context);
           DanhMuc danhmuc = danhMucDAO.getID(String.valueOf(item.getMaDanhMuc()));
            tvgiaSanPham = v.findViewById(R.id.product_price);
            tvNameSanPham = v.findViewById(R.id.product_name);
            anhSanpham = v.findViewById(R.id.product_iamge);
            anhyeuthich = v.findViewById(R.id.icon_favorite);

            tvgiaSanPham.setText(item.getGia() + "");
            tvNameSanPham.setText(item.getTenSanPham());
            anhSanpham.setImageBitmap(item.getAnh());
//            anhyeuthich.setImageResource(item.isYeuThich() ? R.drawable.yeuthich_active : R.drawable.yeuthich_inactive);

        }
        return v;
    }

}
