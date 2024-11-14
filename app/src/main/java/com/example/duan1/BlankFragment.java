package com.example.duan1;

import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BlankFragment extends Fragment {

    private ImageView mainBanner;
    private int[] images = {R.drawable.banner_1, R.drawable.banner_2}; // Thay thế bằng tên ảnh của bạn
    private int currentIndex = 0;

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
        handler.post(runnable); // Bắt đầu slideshow
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Dừng slideshow khi Fragment bị hủy
    }
}