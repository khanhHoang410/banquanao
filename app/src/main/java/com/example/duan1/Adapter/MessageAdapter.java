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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int position) {

        Message message = messageList.get(position);
        if (message.isAdmin()) {
            holder.userMessage.setVisibility(View.GONE);
            holder.adminMessage.setVisibility(View.VISIBLE);
            holder.adminMessage.setText(message.getContent());
        } else {
            holder.adminMessage.setVisibility(View.GONE);
            holder.userMessage.setVisibility(View.VISIBLE);
            holder.userMessage.setText(message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView adminMessage, userMessage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            adminMessage = itemView.findViewById(R.id.adminMessage);
            userMessage = itemView.findViewById(R.id.userMessage);
        }
    }
}
