package com.jnu.bookmanagementsystem;

import static com.jnu.bookmanagementsystem.MainActivity.UID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.jnu.bookmanagementsystem.adapter.BorrowBookAdapter;
import com.jnu.bookmanagementsystem.bean.BookBean;
import com.jnu.bookmanagementsystem.db.service.BookService;
import com.jnu.bookmanagementsystem.db.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class MyBorrowRecordActivity extends AppCompatActivity implements View.OnClickListener {


    private List<BookBean> bookList=new ArrayList<>();
    private BorrowBookAdapter borrowBookAdapter;
    private ListView listView;
    private ImageView returnIv;
    private BookService bookService = new BookServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_borrow_book_remark);

        listView=findViewById(R.id.my_borrow_book_lv);
        returnIv=findViewById(R.id.my_borrow_book_return_iv);
        returnIv.setOnClickListener(this);
        borrowBookAdapter = new BorrowBookAdapter(this, bookList);
        listView.setAdapter(borrowBookAdapter);

        setLVClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        List<BookBean> list = new ArrayList<>();
        list = bookService.findBookByUid(UID);
        //清空账单封装对象数据
        bookList.clear();
        //存入搜索结果账单列表数据
        bookList.addAll(list);
        //启动适配器
        borrowBookAdapter.notifyDataSetChanged();
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_borrow_book_return_iv:
                finish();
                break;
        }
    }
    private void setLVClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //获取正在被点击的这条信息
                BookBean currentBean = bookList.get(position);
                //创建意图对象
                Intent intent = new Intent(MyBorrowRecordActivity.this, DetailActivity.class);
                //设置传递键值对
                intent.putExtra("currentBeanId", currentBean.getId());
                //激活意图,跳转界面
                startActivity(intent);
            }
        });
    }
}

