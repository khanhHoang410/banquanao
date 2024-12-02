package com.example.duan1.Models;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }}