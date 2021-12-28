package com.jnu.bookmanagementsystem;

import static com.jnu.bookmanagementsystem.MainActivity.UID;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jnu.bookmanagementsystem.bean.BookBean;
import com.jnu.bookmanagementsystem.bean.LendBean;
import com.jnu.bookmanagementsystem.db.service.BookService;
import com.jnu.bookmanagementsystem.db.service.LendService;
import com.jnu.bookmanagementsystem.db.service.impl.BookServiceImpl;
import com.jnu.bookmanagementsystem.db.service.impl.LendServiceImpl;

import java.util.Calendar;
import java.util.TimeZone;


public class DetailActivity extends AppCompatActivity {
    private ImageView returnIv;
    private TextView borrowbkTv;
    private TextView moneyTv, bookNameTv, flagTv, bookKindTv, writerTv;
    private BookBean book;
    private LendBean lendBean;
    private LendService lendService = new LendServiceImpl();
    private BookService bookService = new BookServiceImpl();
    private Context context = this;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //获取意图对象
        Intent intent = getIntent();
        //获取传递的账单ID值
        int currentBeanId = intent.getIntExtra("currentBeanId", 0);
        book = bookService.getBookById(currentBeanId);

        //初始化日期
        initDate();

        returnIv = findViewById(R.id.detail_return_iv);
        borrowbkTv = findViewById(R.id.detail_borrowbk_tv);
        returnIv.setClickable(true);
        borrowbkTv.setClickable(true);

        if (book.getFlag() == 0 && book.getUserId() == UID) {
            borrowbkTv.setText("还书");
        }

        returnIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        borrowbkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                if (book.getFlag() == 0 && book.getUserId() == UID) {
                    builder.setTitle("确定要还书吗？");
                }else {
                    builder.setTitle("确定要借阅吗？");
                }
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (book.getFlag() == 0 && book.getUserId() == UID) {
                            book.setFlag(1);
                            book.setUserId(0);
                            bookService.freshBook(book);

                            lendService.delete(UID,book.getId());

                            Toast.makeText(context, "还书成功！", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            if (book.getFlag() == 1) {

                                book.setFlag(0);
                                book.setUserId(UID);
                                book.setYear(year);
                                book.setYear(month);
                                book.setYear(day);
                                bookService.freshBook(book);

                                lendBean=new LendBean();
                                lendBean.setUid(UID);
                                lendBean.setBid(book.getId());
                                lendBean.setBookname(book.getBookName());

                                String monthStr = month + "";
                                String dayStr = day + "";
                                if (month < 10)
                                    monthStr = "0" + monthStr;
                                if (day < 10)
                                    dayStr = "0" + dayStr;
                                String time = year + "-" + monthStr + "-" + dayStr;
                                lendBean.setLendtime(time);

                                lendService.addNote(lendBean);
                                Toast.makeText(context, "借书成功，请在一个月内归还！", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(context, "已借出,借书失败！", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
            }

        });

        bookNameTv = findViewById(R.id.detail_bookName_tv);
        bookKindTv = findViewById(R.id.detail_bookKind_tv);
        writerTv = findViewById(R.id.detail_writer_tv);
        moneyTv = findViewById(R.id.detail_money_tv);
        flagTv = findViewById(R.id.detail_flag_tv);

        bookNameTv.setText(book.getBookName());
        bookKindTv.setText(book.getBookKind());
        writerTv.setText(book.getWriter());
        moneyTv.setText(String.format("%.2f", book.getMoney()));
        if (book.getFlag() == 1)
            flagTv.setText("可借出");
        else
            flagTv.setText("已借出");

    }


    /**
     * 初始化今日日期
     */
    private void initDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

}





