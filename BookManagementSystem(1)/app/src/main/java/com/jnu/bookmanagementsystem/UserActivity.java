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

        // Set the side box
        initSidebar();
        aa = findViewById(R.id.main_return_top);
        // Start setting RecyclerView
        recyclerView = (RecyclerView) this.findViewById(R.id.user_main_rv);
        // Set a fixed size
        recyclerView.setHasFixedSize(true);
        // Set the RecyclerView layout manager
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        // Create the adapter and set it
        bookAdapter = new BookAdapter(list, this);
        bookAdapter.setHasStableIds(true);
        recyclerView.setAdapter(bookAdapter);
        // Set the response event
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
     * ??????????????????
     */
    private void initSidebar() {
        //??????????????????
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationview = findViewById(R.id.navigation_view);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //???toolbar???ActionBar??????
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);//???????????????
//        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Home home = new Home();
        home.initNV(navigationview, drawer, this);
    }

    /**
     * ??????recycleview???ListView?????????
     * ???????????????????????????????????????????????????
     */
    private void initListener() {
        bookAdapter.setItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                //????????????????????????????????????
                BookBean currentBean = list.get(position);
                //??????????????????
                Intent intent = new Intent(UserActivity.this, DetailActivity.class);
                //?????????????????????
                intent.putExtra("currentBeanId", currentBean.getId());
                //????????????,????????????
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_return_top:
                refresh();
                Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();
                break;
        }
    }
}