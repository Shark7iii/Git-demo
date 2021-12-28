package com.jnu.bookmanagementsystem.db.dao;

import com.jnu.bookmanagementsystem.bean.LendBean;

import java.util.List;

public interface LendDao {
    void addOne(LendBean lendBean);

    List<LendBean> findAll();

    void deleteRecord(int uid, int bid);
}
