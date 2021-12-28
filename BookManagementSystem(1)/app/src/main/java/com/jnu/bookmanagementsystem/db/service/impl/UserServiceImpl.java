package com.jnu.bookmanagementsystem.db.service.impl;


import com.jnu.bookmanagementsystem.db.dao.UserDao;
import com.jnu.bookmanagementsystem.db.dao.impl.UserDaoImpl;
import com.jnu.bookmanagementsystem.db.service.UserService;



public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(String username, String password, int kind) {
        return userDao.register(username,password,kind);
    }

    @Override
    public int login(String username, String password) {
        return userDao.login(username,password);
    }

    @Override
    public int findKindById(int uid) {
        return userDao.findKind(uid);
    }


}
