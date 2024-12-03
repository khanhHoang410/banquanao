package com.example.duan1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duan1.Adapter.SanPhamRecyclerViewAdapter;
import com.example.duan1.Dao.SanPhamDAO;
import com.example.duan1.Models.MyViewModel;
import com.example.duan1.Models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment extends Fragment {
    SanPhamRecyclerViewAdapter sanphamAdapter;
    private MyViewModel viewModel;
    private OnYeuThichChangeListener listener;
    SanPham sanPham;
    private int userId;
    List<SanPham> yeuthichList = new ArrayList<>();
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    private ImageView mainBanner;
    private int[] images = {R.drawable.banner_1, R.drawable.banner_2}; // Thay thế bằng tên ảnh của bạn
    private int currentIndex = 0;
    RecyclerView recycler_view_tshirts;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mainBanner.setImageResource(images[currentIndex]);
            currentIndex = (currentIndex + 1) % images.length; // Quay vòng qua các ảnh
            handler.postDelayed(this, 3000); // Thay đổi sau 3 giây
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        mainBanner = view.findViewById(R.id.main_banner);
        recycler_view_tshirts = view.findViewById(R.id.recycler_view_tshirts);
        handler.post(runnable); // Bắt đầu slideshow

        viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        userId = viewModel.getUserId();
        recycler_view_tshirts.setLayoutManager(new LinearLayoutManager(getContext()));
        sanPhamDAO = new SanPhamDAO(getContext());
        list = sanPhamDAO.getAll();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        sanphamAdapter = new SanPhamRecyclerViewAdapter(getContext(), list,this::updateYeuThichList,userId); // Dùng RecyclerView.Adapter
        recycler_view_tshirts.setAdapter(sanphamAdapter);
        recycler_view_tshirts.setLayoutManager(layoutManager);
        sanphamAdapter.updateData(list);
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Dừng slideshow khi Fragment bị hủy
    }
    public void updateYeuThichList(SanPham sanPham){
        if (sanPham.getYeuThich()){
            if (!yeuthichList.contains(sanPham)){
                Log.d("updateYeuThichList", "Thêm sản phẩm vào danh sách yêu thích: " + sanPham.getTenSanPham());
                yeuthichList.add(sanPham);
            }
        }else {
            Log.d("updateYeuThichList", "Xóa sản phẩm khỏi danh sách yêu thích: " + sanPham.getTenSanPham());
            yeuthichList.remove(sanPham);
        }
        if (listener != null) {
            listener.onYeuThichChange(yeuthichList); // Truyền yeuthichList đã cập nhật
        }
    }

    public interface OnYeuThichChangeListener {
        void onYeuThichChange(List<SanPham> yeuthichList);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnYeuThichChangeListener) {
            listener = (OnYeuThichChangeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnYeuThichChangeListener");
        }
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof SanPhamRecyclerViewAdapter.OnYeuThichChangeListener) {
//            listener = (SanPhamRecyclerViewAdapter.OnYeuThichChangeListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnYeuThichChangeListener");
//        }
//    }

}
