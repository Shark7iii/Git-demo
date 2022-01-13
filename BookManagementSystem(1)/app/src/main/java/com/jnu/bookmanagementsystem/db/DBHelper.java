package com.jnu.bookmanagementsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.jnu.bookmanagementsystem.R;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    //    创建数据库的方法，只有项目第一次运行时，会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create user table
        String user_table = "create table user(uid integer primary key autoincrement," +
                "username varchar(20)," +
                "password varchar(20)," +
                "kind integer default 0)";
        db.execSQL(user_table);

        // Create book table
        String book_table = "create table book(id integer primary key autoincrement," +
                "imageId integer," +
                "bookName varchar(30)," +
                "bookKind varchar(30)," +
                "writer varchar(20)," +
                "money float," +
                "time varchar(20)," +
                "year integer," +
                "month integer," +
                "day integer," +
                "userId integer default 0," +
                "flag integer)";

        db.execSQL(book_table);

        // Create lend table
        String lend_table = "create table lend(id integer primary key autoincrement," +
                "uid integer," +
                "username varchar(30)," +
                "bid integer," +
                "bookname varchar(30)," +
                "lendtime varchar(30))";

        db.execSQL(lend_table);

        insertType(db);
    }


    private void insertType(SQLiteDatabase database) {
        String sql2 = "insert into user(uid,username,password,kind) values (999,'admin','123',1)";
        database.execSQL(sql2);
//        String sql = "insert into book(bookName,imageId,writer) values ('软件工程'," + R.drawable.book_1 + ",'金庸')";
        String sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values(" + R.drawable.book_1 + " ,'软件工程2','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程3','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程4','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程5','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程6','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程7','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程8','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程9','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程10','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程11','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程12','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程13','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程14','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程15','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程16','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);
        sql = "insert into book(imageId,bookName,bookKind,writer,money,time,year,month,day,flag) values (" + R.drawable.book_1 + " ,'软件工程17','学习','金庸',100.0,'1990-01-01',1990,1,1,1)";
        database.execSQL(sql);


    }

    // 数据库版本在更新时发生改变，会调用此方法
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}


