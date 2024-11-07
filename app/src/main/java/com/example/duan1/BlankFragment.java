package com.example.duan1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BlankFragment extends Fragment {

    private ImageView slideshowImageView;
    private int[] imageArray = {R.drawable.banner_1, R.drawable.banner_2}; // Đường dẫn đến 2 ảnh trong thư mục drawable
    private int currentIndex = 0;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        // Initialize the ImageView
        slideshowImageView = view.findViewById(R.id.slideshowImageView);

        // Tạo runnable để chuyển đổi ảnh
        runnable = new Runnable() {
            @Override
            public void run() {
                // Chuyển đổi ảnh trong imageArray
                currentIndex = (currentIndex + 1) % imageArray.length;
                slideshowImageView.setImageResource(imageArray[currentIndex]);

                // Lặp lại sau 3 giây
                handler.postDelayed(this, 3000);
            }
        };

        // Bắt đầu slideshow
        handler.post(runnable);

        return view;  // Trả về view để hiển thị fragment
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Dừng Handler khi fragment bị hủy để tránh rò rỉ bộ nhớ
        handler.removeCallbacks(runnable);
    }
}
