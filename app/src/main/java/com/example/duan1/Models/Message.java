package com.example.duan1.Models;

public class Message {
    private String content;
    private boolean isAdmin;

    public Message(String content, boolean isAdmin) {
        this.content = content;
        this.isAdmin = isAdmin;
    }

    public String getContent() {
        return content;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

