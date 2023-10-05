package com.example.userregister.presenter;

import com.example.userregister.pojo.User;

import java.util.List;

public interface UserListView {
    void showData(List<User> users);
    void showError(String mess);

    void completeLoad(String text);
}
