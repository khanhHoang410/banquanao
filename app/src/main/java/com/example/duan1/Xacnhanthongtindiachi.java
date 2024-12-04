package com.example.duan1;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duan1.Dao.DonHangDAO;
import com.example.duan1.Models.DonHang;

import java.util.Date;

public class Xacnhanthongtindiachi extends AppCompatActivity {
    TextView tv_total_value,tv_address_value;
    TextView tv_return_to_customer_info;
    Button btn_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xacnhanthongtindiachi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv_total_value = findViewById(R.id.tv_total_value);
        tv_address_value = findViewById(R.id.tv_address_value);
        btn_continue = findViewById(R.id.btn_continue);
        int maDonHang = getIntent().getIntExtra("maDonHang", -1);


        DonHangDAO donHangDAO =new DonHangDAO(this);
        DonHang donHang = donHangDAO.getById(maDonHang);
        int maNguoidung = donHang.getMaNguoiDung();
        String diaChi = donHang.getDiaChi();
        float tongTien = donHang.getTongTien();
        tv_total_value.setText("$"+tongTien);
        tv_address_value.setText(diaChi);

        tv_return_to_customer_info = findViewById(R.id.tv_return_to_customer_info);
        tv_return_to_customer_info.setOnClickListener(view -> {
            finish();
        });
        btn_continue.setOnClickListener(v -> {
            Toast.makeText(Xacnhanthongtindiachi.this, "Đơn hàng của bạn đã được xử lý", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Xacnhanthongtindiachi.this, DonhangActivity.class);
//            intent.putExtra("maDonHang", maDonHang);
//            startActivity(intent);
            startActivity(new Intent(Xacnhanthongtindiachi.this,MainActivity.class));
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("maNguoidung", maNguoidung);
            editor.apply();
            GuiThongBao();
            finish();
        });
    }
    void GuiThongBao (){
        //Khai báo Intent để chạy activity MSG khi người dùng bấm vào notify
        Intent intentDemo = new Intent(getApplicationContext(), MainActivity.class);
        // tạo biến cờ xác định phạm vi hoạt động
        intentDemo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // gửi dữ liệu vào Intent để trong activity MSG nhận dữ liệu
        intentDemo.putExtra("dulieu","Nội dung gửi từ Notify vào màn MSG");
        // tạo stack để chứa các activity
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Xacnhanthongtindiachi.this);
        stackBuilder.addNextIntentWithParentStack( intentDemo );
        //lấy pendingIntent để gửi vào notify
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // khởi tạo bitmao để đọc ảnh , viết trc dòng khỏi tạo cho layout notify
        Bitmap anh = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);



        // khởi tạo layout cho notify
        Notification customNotify = new NotificationCompat.Builder(
                Xacnhanthongtindiachi.this, NotifyConfig.CHANEL_ID
        )
                .setSmallIcon(R.drawable.logo_ndk)
                .setContentTitle("Tiêu đề thông báo")
                .setContentText("Đã đặt đơn hàng")
                .setContentIntent( pendingIntent )// cho phép gọi activity
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(anh).bigLargeIcon(anh))
                .build();
// khởi tạo trình quản lý notify
        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from( Xacnhanthongtindiachi.this);
        // Kiểm tra quyền gửi thông báo đã được cấp hay chưa
        if(ActivityCompat.checkSelfPermission(Xacnhanthongtindiachi.this,
                android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
        ){
            // chưa đươc cấp quyền ==> hiện giao diện cấp quyền
            ActivityCompat.requestPermissions(Xacnhanthongtindiachi.this,
                    new String[] { android.Manifest.permission.POST_NOTIFICATIONS },
                    999 );
            // thoát khỏi hamf
            return;

        }else{
            // đã cấp quyên
            int id_notify = (int) new Date().getTime();// tạo chuỗi số traánh trùng
            // hiển thị notify
            notificationManagerCompat.notify( id_notify, customNotify );
        }
    }
}