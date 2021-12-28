package com.jnu.bookmanagementsystem.db.service.impl;

import com.jnu.bookmanagementsystem.bean.BookBean;
import com.jnu.bookmanagementsystem.db.dao.BookDao;
import com.jnu.bookmanagementsystem.db.dao.impl.BookDaoImpl;
import com.jnu.bookmanagementsystem.db.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public BookBean getBookById(int id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<BookBean> findBookByUid(int uid) {
        return bookDao.getBookByUid(uid);
    }

    @Override
    public void deleteBook(int id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void freshBook(BookBean book) {
        bookDao.updateBook(book);
    }

    @Override
    public List<BookBean> findBook(String bookNameText) {
        return bookDao.getBook(bookNameText);
    }

    @Override
    public void addBook(BookBean book) {
        bookDao.addNewBook(book);
    }
}
