package com.jnu.bookmanagementsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DBController {

    private static SQLiteDatabase database;

    /* 初始化数据库对象*/
    public static void initDatebase(Context context) {
        DBHelper helper = new DBHelper(context);  //得到帮助类对象
        database = helper.getWritableDatabase();      //得到数据库对象
    }

    public static SQLiteDatabase getDatabase() {
        return database;
    }
}