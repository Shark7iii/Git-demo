package com.jnu.bookmanagementsystem.db.dao.impl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jnu.bookmanagementsystem.R;
import com.jnu.bookmanagementsystem.bean.BookBean;
import com.jnu.bookmanagementsystem.db.DBController;
import com.jnu.bookmanagementsystem.db.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private SQLiteDatabase sdb = DBController.getDatabase();

    /**
     * 根据相关信息搜索匹配到的书籍
     * @param match
     * @return
     */
    @Override
    @SuppressLint("Range")
    public List<BookBean> getBook(String match) {
        String sql = "select * from book where  bookName like ? or bookKind like ? or writer like ? ";
        List<BookBean> list = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = sdb.rawQuery(sql, new String[]{"%" + match + "%", "%" + match + "%", "%" + match + "%"});
            //遍历符合要求的每一行数据
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
                String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
                String bookKind = cursor.getString(cursor.getColumnIndex("bookKind"));
                String writer = cursor.getString(cursor.getColumnIndex("writer"));
                float money = cursor.getFloat(cursor.getColumnIndex("money"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                int year = cursor.getInt(cursor.getColumnIndex("year"));
                int month = cursor.getInt(cursor.getColumnIndex("month"));
                int day = cursor.getInt(cursor.getColumnIndex("day"));
                int userId = cursor.getInt(cursor.getColumnIndex("userId"));
                int flag = cursor.getInt(cursor.getColumnIndex("flag"));
                BookBean bookBean = new BookBean(id, imageId, bookName, bookKind, writer, money, time, year, month, day,userId, flag);
                list.add(bookBean);
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

    /**
     * 根据书籍的ID查询书籍
     * @param id
     * @return
     */
    @Override
    @SuppressLint("Range")
    public BookBean getBookById(int id) {
        String sql = "select * from book where  id = ? ";
        BookBean bookBean = null;
        Cursor cursor = null;
        try {
            cursor = sdb.rawQuery(sql, new String[]{id + ""});
            cursor.moveToLast();
            int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
            String bookKind = cursor.getString(cursor.getColumnIndex("bookKind"));
            String writer = cursor.getString(cursor.getColumnIndex("writer"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            int year = cursor.getInt(cursor.getColumnIndex("year"));
            int month = cursor.getInt(cursor.getColumnIndex("month"));
            int day = cursor.getInt(cursor.getColumnIndex("day"));
            int userId = cursor.getInt(cursor.getColumnIndex("userId"));
            int flag = cursor.getInt(cursor.getColumnIndex("flag"));
            bookBean = new BookBean(id, imageId, bookName, bookKind, writer, money, time, year, month, day, userId, flag);
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
        return bookBean;
    }

    /**
     * 更新书籍，或者说修改书籍
     * @param book
     */
    @Override
    public void updateBook(BookBean book) {
        ContentValues values = new ContentValues();

        values.put("id", book.getId());
        values.put("imageId", book.getImageId());
        values.put("bookName", book.getBookName());
        values.put("bookKind", book.getBookKind());
        values.put("writer", book.getWriter());
        values.put("money", book.getMoney());
        values.put("time", book.getTime());
        values.put("year", book.getYear());
        values.put("month", book.getMonth());
        values.put("day", book.getDay());
        values.put("userId", book.getUserId());
        values.put("flag", book.getFlag());

        try {
            sdb.update("book", values, "id = ?", new String[]{book.getId() + ""});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加新书
     * @param book
     */
    @Override
    public void addNewBook(BookBean book) {
        try {
            if (sdb.isOpen()) {
                ContentValues values = new ContentValues();

                values.put("imageId", book.getImageId());
                values.put("bookName", book.getBookName());
                values.put("bookKind", book.getBookKind());
                values.put("writer", book.getWriter());
                values.put("money", book.getMoney());
                values.put("time", book.getTime());
                values.put("year", book.getYear());
                values.put("month", book.getMonth());
                values.put("day", book.getDay());
                values.put("userId", book.getUserId());
                values.put("flag", book.getFlag());

                sdb.insert("book", null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        try {
            sdb.delete("book", "id = ?", new String[]{id + ""});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("Range")
    public List<BookBean> getBookByUid(int uid) {
        String sql = "select * from book where  userId =? ";
        List<BookBean> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = sdb.rawQuery(sql, new String[]{ uid + ""});
            //遍历符合要求的每一行数据
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
                String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
                String bookKind = cursor.getString(cursor.getColumnIndex("bookKind"));
                String writer = cursor.getString(cursor.getColumnIndex("writer"));
                float money = cursor.getFloat(cursor.getColumnIndex("money"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                int year = cursor.getInt(cursor.getColumnIndex("year"));
                int month = cursor.getInt(cursor.getColumnIndex("month"));
                int day = cursor.getInt(cursor.getColumnIndex("day"));
                int userId = cursor.getInt(cursor.getColumnIndex("userId"));
                int flag = cursor.getInt(cursor.getColumnIndex("flag"));
                BookBean bookBean = new BookBean(id, imageId, bookName, bookKind, writer, money, time, year, month, day, userId, flag);
                list.add(bookBean);
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

}
