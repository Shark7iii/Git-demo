package com.jnu.bookmanagementsystem.db.service;

import com.jnu.bookmanagementsystem.bean.LendBean;

import java.util.List;

public interface LendService {
    void addNote(LendBean lendBean);

    List<LendBean> findLend();

    void delete(int uid, int id);
}
