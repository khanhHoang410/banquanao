package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.ChatAdapter;
import com.example.duan1.Models.Message;  // Đảm bảo rằng bạn import đúng class Message trong project của bạn
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerViewChat;
    EditText editTextMessage;
    ImageButton buttonSend, backbutton;
    ChatAdapter chatAdapter;
    private DatabaseReference chatRef;
    private List<Message> messages ;
    private String userEmail = "namvu@gmail.com";
    private String adminEmail = "admin@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        backbutton = findViewById(R.id.back_button);
        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        messages = new ArrayList<>();

        // Thay thế dấu chấm (".") trong email để phù hợp với yêu cầu đường dẫn của Firebase
        String sanitizedUserEmail = userEmail.replace(".", "_");
        String sanitizedAdminEmail = adminEmail.replace(".", "_");

        // Khởi tạo DatabaseReference trỏ tới đúng vị trí trong Firebase
        chatRef = FirebaseDatabase.getInstance().getReference("chats")
                .child(sanitizedUserEmail + "_" + sanitizedAdminEmail)
                .child("messages");

        // Thiết lập RecyclerView
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(messages, userEmail,ChatActivity.this);
        recyclerViewChat.setAdapter(chatAdapter);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextMessage.getText().toString();
                if (!messageText.isEmpty()) {
                    sendMessage(messageText, userEmail);
                    editTextMessage.setText("");
                }
            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatActivity.this, MainActivity.class));
            }
        });

        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Message message = data.getValue(Message.class);
                    messages.add(message);
                }
                chatAdapter.notifyDataSetChanged();
                recyclerViewChat.scrollToPosition(messages.size() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage(String messageText, String senderEmail) {

        // Tạo một ID duy nhất cho mỗi tin nhắn
        String messageId = chatRef.push().getKey();

        // Tạo một đối tượng Message để lưu trữ thông tin của tin nhắn
        Message message = new Message(senderEmail, messageText, System.currentTimeMillis());

        // Lưu trữ tin nhắn vào Database dưới messageId duy nhất này
        if (messageId != null) {
            chatRef.child(messageId).setValue(message);
        }
    }


}
