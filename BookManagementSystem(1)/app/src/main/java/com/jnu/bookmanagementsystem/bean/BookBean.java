package com.jnu.bookmanagementsystem.bean;

public class BookBean {
    //书籍编号，标识符
    private int id;
    //图片资源
    private int imageId;
    //书名
    private String bookName;
    //书籍分类
    private String bookKind;
    //作者
    private String writer;
    //书籍价格
    private float money;
    //最近借出的时间
    private String time;

    //最近借出的时间
    private int year;
    private int month;
    private int day;
    private int userId = 0;

    //已经借出记作0
    private int flag;


    public BookBean() {

    }

    @Override
    public String toString() {
        return "BookBean{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", bookName='" + bookName + '\'' +
                ", bookKind='" + bookKind + '\'' +
                ", writer='" + writer + '\'' +
                ", money=" + money +
                ", time='" + time + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", userId=" + userId +
                ", flag=" + flag +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BookBean(int id, int imageId, String bookName, String bookKind, String writer, float money, String time, int year, int month, int day, int userId, int flag) {
        this.id = id;
        this.imageId = imageId;
        this.bookName = bookName;
        this.bookKind = bookKind;
        this.writer = writer;
        this.money = money;
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
        this.userId = userId;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookKind() {
        return bookKind;
    }

    public void setBookKind(String bookKind) {
        this.bookKind = bookKind;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
