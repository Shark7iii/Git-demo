package com.jnu.bookmanagementsystem.db.dao.impl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jnu.bookmanagementsystem.bean.LendBean;
import com.jnu.bookmanagementsystem.db.DBController;
import com.jnu.bookmanagementsystem.db.dao.LendDao;

import java.util.ArrayList;
import java.util.List;

public class LendDaoImpl implements LendDao {
    private SQLiteDatabase sdb = DBController.getDatabase();

    @Override
    public void addOne(LendBean lendBean) {
        try {
            if (sdb.isOpen()) {
                ContentValues values = new ContentValues();

                values.put("uid", lendBean.getUid());
                values.put("username", lendBean.getUsername());
                values.put("bid", lendBean.getBid());
                values.put("bookname", lendBean.getBookname());
                values.put("lendtime", lendBean.getLendtime());

                sdb.insert("lend", null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    @SuppressLint("Range")
    public List<LendBean> findAll() {
        List<LendBean> list = new ArrayList<>();
        String sql = "select * from lend where 1 = 1";
        Cursor cursor = null;
        try {
            cursor = sdb.rawQuery(sql, null);
            //遍历符合要求的每一行数据
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int uid = cursor.getInt(cursor.getColumnIndex("uid"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                int bid = cursor.getInt(cursor.getColumnIndex("bid"));
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                String lendtime = cursor.getString(cursor.getColumnIndex("lendtime"));

                LendBean LendBean = new LendBean(id, uid, username, bid, bookname, lendtime);
                list.add(LendBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cursor != null)
                    cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void deleteRecord(int uid, int bid) {
        try {
            sdb.delete("lend", "uid = ? and bid = ?", new String[]{uid + "", bid + ""});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
