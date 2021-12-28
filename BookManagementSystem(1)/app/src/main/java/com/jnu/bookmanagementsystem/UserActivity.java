package com.jnu.bookmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.jnu.bookmanagementsystem.adapter.BookAdapter;
import com.jnu.bookmanagementsystem.bean.BookBean;
import com.jnu.bookmanagementsystem.db.DBController;

import com.jnu.bookmanagementsystem.db.service.BookService;
import com.jnu.bookmanagementsystem.db.service.impl.BookServiceImpl;
import com.jnu.bookmanagementsystem.util.ScreenInfoUtils;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private SQLiteDatabase db = DBController.getDatabase();
    private BookService bookService = new BookServiceImpl();
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private TextView aa;
    private List<BookBean> list = new ArrayList<>();
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenInfoUtils.fullScreen(this);
        setContentView(R.layout.activity_user);


        //设置侧边框
        initSidebar();
        aa = findViewById(R.id.main_return_top);
        //开始设置RecyclerView
        recyclerView = (RecyclerView) this.findViewById(R.id.user_main_rv);
        //设置固定大小
        recyclerView.setHasFixedSize(true);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //创建适配器并且设置
        bookAdapter = new BookAdapter(list, this);
        bookAdapter.setHasStableIds(true);
        recyclerView.setAdapter(bookAdapter);
        //设置响应事件
        initListener();
        aa.setOnClickListener(this);
    }

    //    @Override
    protected void onResume() {
        super.onResume();
        setBook();
    }


    private void refresh() {
        finish();
        Intent intent = new Intent(UserActivity.this, UserActivity.class);
        startActivity(intent);
    }

    private void setBook() {
        list.clear();
        for (int id = 2; id < 10; id++) {
            BookBean book = bookService.getBookById(id);
            if (null != book && book.getId() != 0)
                list.add(book);
        }
        bookAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化侧边框
     */
    private void initSidebar() {
        //左侧滑动实现
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationview = findViewById(R.id.navigation_view);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //将toolbar与ActionBar关联
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);//初始化状态
//        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Home home = new Home();
        home.initNV(navigationview, drawer, this);
    }

    /**
     * 由于recycleview和ListView不一样
     * 需要自定义设置的单击和长按事件响应
     */
    private void initListener() {
        bookAdapter.setItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                //获取正在被点击的这条信息
                BookBean currentBean = list.get(position);
                //创建意图对象
                Intent intent = new Intent(UserActivity.this, DetailActivity.class);
                //设置传递键值对
                intent.putExtra("currentBeanId", currentBean.getId());
                //激活意图,跳转界面
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_return_top:
                refresh();
                Toast.makeText(getApplicationContext(), "刷新成功", Toast.LENGTH_LONG).show();
                break;
        }
    }
}