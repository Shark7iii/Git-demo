package com.jnu.bookmanagementsystem.db.dao;

import com.jnu.bookmanagementsystem.bean.BookBean;

import java.util.List;

public interface BookDao {
    List<BookBean> getBook(String match);

    BookBean getBookById(int id);

    void updateBook(BookBean book);
    List<BookBean> getBookByUid(int uid);
    void addNewBook(BookBean book);

    void deleteBook(int id);
}
