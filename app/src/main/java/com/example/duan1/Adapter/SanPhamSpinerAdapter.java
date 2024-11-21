//package com.example.duan1.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.example.duan1.Models.SanPham;
//import com.example.duan1.R;
//
//import java.util.ArrayList;
//
//public class SanPhamSpinerAdapter extends ArrayAdapter<SanPham> {
//    private Context context;
//    private ArrayList<SanPham> lists;
//    TextView tvName,tvGia;
//    ImageView anhSanPham,anhYeuThich;
//    public SanPhamSpinerAdapter(@NonNull Context context, ArrayList<SanPham> lists) {
//        super(context, 0,lists);
//        this.context = context;
//        this.lists = lists;
//    }
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull android.view.ViewGroup parent){
//        View v= convertView;
//        if (v==null){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = inflater.inflate(R.layout.item_san_pham,null);
//        }
//       final SanPham item = lists.get(position);
//        if (item!=null){
//         tvName = v.findViewById(R.id.product_name);
//         tvGia = v.findViewById(R.id.product_price);
//         anhSanPham = v.findViewById(R.id.product_iamge);
//         anhYeuThich = v.findViewById(R.id.icon_favorite);
//         tvName.setText(item.getTenSanPham());
//         tvGia.setText(item.getGia()+"");
//            anhSanPham.setImageBitmap(item.getAnh());
//        }
//        return  v;
//
//    }
//
//    @Override
//    public View getDropDownView(int position, @Nullable View convertView, @NonNull android.view.ViewGroup parent){
//        View v = convertView;
//        if (v==null){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = inflater.inflate(R.layout.item_san_pham,null);
//        }
//        final SanPham item = lists.get(position);
//        if (item!=null){
//            tvName = v.findViewById(R.id.product_name);
//            tvGia = v.findViewById(R.id.product_price);
//            anhSanPham = v.findViewById(R.id.product_iamge);
//            anhYeuThich  =v.findViewById(R.id.icon_favorite);
//
//            tvName.setText(item.getTenSanPham());
//            tvGia.setText(item.getGia() + "");
//            anhSanPham.setImageBitmap(item.getAnh());
//        }
//        return  v;
//    }
//
//
//}
