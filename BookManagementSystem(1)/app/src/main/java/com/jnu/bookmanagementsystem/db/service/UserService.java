package com.jnu.bookmanagementsystem.db.service;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

public interface UserService {

    /**
     * 注册方法
     *
     * @param username
     * @param password
     * @param kind
     * @return
     */
    boolean register(String username, String password, int kind);

    /**
     * 登录功能
     *
     * @param username
     * @param password
     * @return
     */
    int login(String username, String password);

    int findKindById(int uid);
}
