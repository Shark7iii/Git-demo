package com.jnu.bookmanagementsystem.db.service;

import com.jnu.bookmanagementsystem.bean.BookBean;

import java.util.List;

public interface BookService {
    BookBean getBookById(int id);

    void freshBook(BookBean book);

    List<BookBean> findBook(String bookNameText);

    void addBook(BookBean book);

    List<BookBean> findBookByUid(int uid);

    void deleteBook(int id);
}
