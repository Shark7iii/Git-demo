package com.jnu.bookmanagementsystem.db.dao;

public interface UserDao {
    /**
     * 注册方法
     * @param username
     * @param password
     * @param kind
     * @return
     */
    boolean register(String username, String password, int kind) ;

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    int login(String username, String password);

    int findKind(int uid);
}
