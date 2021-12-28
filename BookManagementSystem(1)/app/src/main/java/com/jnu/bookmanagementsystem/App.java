package com.jnu.bookmanagementsystem;/* 表示全局应用的类*/

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.jnu.bookmanagementsystem.db.DBController;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化数据库
        DBController.initDatebase(getApplicationContext());
        //
    }
}
