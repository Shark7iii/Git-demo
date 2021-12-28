package com.jnu.bookmanagementsystem.db.dao.impl;

import android.annotation.SuppressLint;
import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jnu.bookmanagementsystem.db.DBController;

import com.jnu.bookmanagementsystem.db.dao.UserDao;

public class UserDaoImpl implements UserDao {
    private SQLiteDatabase sdb = DBController.getDatabase();

    //登录用
    public int login(String username, String password) {
        String sql = "select * from user where username=? and password=?";

        Cursor cursor = null;
        try {
            cursor = sdb.rawQuery(sql, new String[]{username, password});
            if (cursor.moveToFirst() == true) {
                @SuppressLint("Range") int uid = cursor.getInt(cursor.getColumnIndex("uid"));
                cursor.close();
                return uid;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return 0;
    }

    @Override
    @SuppressLint("Range")
    public int findKind(int uid) {
        String sql = "select * from user where uid = ?";
        int kind = 100;
        Cursor cursor = null;
        try {
            cursor = sdb.rawQuery(sql, new String[]{uid + ""});
            cursor.moveToLast();
            kind = cursor.getInt(cursor.getColumnIndex("kind"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return kind;

    }

    //注册用
    @SuppressLint("Range")
    public boolean register(String username, String password, int kind) {
        String check = "select * from user where username=?";

        Cursor cursor = null;
        try {
            cursor = sdb.rawQuery(check, new String[]{username});
            if (cursor.moveToNext() == true) {
                cursor.close();
                return false;
            }
            String sql = "insert into user(username,password,kind) values(?,?,?)";
            Object obj[] = {username, password, kind};
            sdb.execSQL(sql, obj);
            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("password", password);
            values.put("kind", kind);
            sdb.insert("user", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return true;
    }
}
