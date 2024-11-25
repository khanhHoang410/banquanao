package com.example.duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Models.Message;
import com.example.duan1.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_USER = 1;
    private static final int VIEW_TYPE_ADMIN = 2;

    List<Message> messageList;
    String currentUserEmail;
    private String adminEmail = "admin@gmail.com";

    public ChatAdapter(List<Message> messageList, String currentUserEmail) {
        this.messageList = messageList;
        this.currentUserEmail = currentUserEmail;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        return message.getSender().equals(adminEmail) ? VIEW_TYPE_ADMIN : VIEW_TYPE_USER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_USER) {
            View view = inflater.inflate(R.layout.item_user_message, parent, false);
            return new UserMessageViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_admin_message, parent, false);
            return new AdminMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (holder instanceof UserMessageViewHolder) {
            ((UserMessageViewHolder) holder).bind(message);
        } else if (holder instanceof AdminMessageViewHolder) {
            ((AdminMessageViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() { return messageList.size(); }

    class UserMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage;
        public UserMessageViewHolder(@NonNull View itemView) { super(itemView); textViewMessage = itemView.findViewById(R.id.tvMessage); }
        public void bind(Message message) { textViewMessage.setText(message.getMessage()); }
    }

    class AdminMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage;
        public AdminMessageViewHolder(@NonNull View itemView) { super(itemView); textViewMessage = itemView.findViewById(R.id.tvMessage); }
        public void bind(Message message) { textViewMessage.setText(message.getMessage()); }
    }
}
