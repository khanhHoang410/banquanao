package com.example.duan1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.MessageAdapter;
import com.example.duan1.Models.Message;  // Đảm bảo rằng bạn import đúng class Message trong project của bạn

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerViewChat;
    EditText editTextMessage;
    ImageButton buttonSend;
    MessageAdapter messageAdapter;
    List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // Khởi tạo danh sách tin nhắn và adapter
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);

        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChat.setAdapter(messageAdapter);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextMessage.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    // Thêm tin nhắn của người dùng
                    messageList.add(new Message(messageText, false));
                    messageAdapter.notifyItemInserted(messageList.size() - 1);
                    recyclerViewChat.scrollToPosition(messageList.size() - 1);
                    editTextMessage.setText("");

                    // Giả lập phản hồi từ admin
                    messageList.add(new Message("Trợ lý sẽ trả lời sớm.", true));
                    messageAdapter.notifyItemInserted(messageList.size() - 1);
                    recyclerViewChat.scrollToPosition(messageList.size() - 1);
                }
            }
        });
    }
}
