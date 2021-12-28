package com.jnu.bookmanagementsystem.bean;

public class LendBean {
    int id;
    int uid;
    String username;
    int bid;
    String bookname;
    String lendtime;

    public LendBean() {
    }

    public LendBean(int id, int uid, String username, int bid, String bookname, String lendtime) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.bid = bid;
        this.bookname = bookname;
        this.lendtime = lendtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getLendtime() {
        return lendtime;
    }

    public void setLendtime(String lendtime) {
        this.lendtime = lendtime;
    }

    @Override
    public String toString() {
        return "LendBean{" +
                "id=" + id +
                ", uid=" + uid +
                ", username='" + username + '\'' +
                ", bid=" + bid +
                ", bookname='" + bookname + '\'' +
                ", lendtime='" + lendtime + '\'' +
                '}';
    }


}
