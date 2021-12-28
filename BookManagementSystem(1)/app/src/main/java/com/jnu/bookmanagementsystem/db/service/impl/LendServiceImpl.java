package com.jnu.bookmanagementsystem.db.service.impl;

import com.jnu.bookmanagementsystem.bean.LendBean;
import com.jnu.bookmanagementsystem.db.dao.LendDao;
import com.jnu.bookmanagementsystem.db.dao.impl.LendDaoImpl;
import com.jnu.bookmanagementsystem.db.service.LendService;

import java.util.List;

public class LendServiceImpl implements LendService {
    private LendDao lendDao = new LendDaoImpl();

    @Override
    public void addNote(LendBean lendBean) {
        lendDao.addOne(lendBean);
    }

    @Override
    public List<LendBean> findLend() {
        return lendDao.findAll();
    }

    @Override
    public void delete(int uid, int bid) {
        lendDao.deleteRecord(uid,bid);
    }
}
