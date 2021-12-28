package com.jnu.bookmanagementsystem.Librarian;

import static com.jnu.bookmanagementsystem.MainActivity.UID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jnu.bookmanagementsystem.DetailActivity;
import com.jnu.bookmanagementsystem.MyBorrowRecordActivity;
import com.jnu.bookmanagementsystem.R;
import com.jnu.bookmanagementsystem.adapter.BorrowBookAdapter;
import com.jnu.bookmanagementsystem.adapter.LibrarianLendRecordAdapter;
import com.jnu.bookmanagementsystem.bean.LendBean;
import com.jnu.bookmanagementsystem.db.service.BookService;
import com.jnu.bookmanagementsystem.db.service.LendService;
import com.jnu.bookmanagementsystem.db.service.impl.BookServiceImpl;
import com.jnu.bookmanagementsystem.db.service.impl.LendServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Librarian_Lend_Record_Activity extends AppCompatActivity implements View.OnClickListener {

    private LibrarianLendRecordAdapter librarianLendRecordAdapter;
    private LendService lendService = new LendServiceImpl();
    private List<LendBean> lendList = new ArrayList<>();

    private ListView listView;
    private ImageView returnIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_lend_record);


        listView = findViewById(R.id.lend_record_lv);
        returnIv = findViewById(R.id.lend_record_return_iv);
        returnIv.setOnClickListener(this);
        librarianLendRecordAdapter = new LibrarianLendRecordAdapter(this, lendList);
        listView.setAdapter(librarianLendRecordAdapter);

        setLVClickListener();


    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        List<LendBean> list = new ArrayList<>();
        list = lendService.findLend();
        //清空账单封装对象数据
        lendList.clear();
        //存入搜索结果账单列表数据
        lendList.addAll(list);
        //启动适配器
        librarianLendRecordAdapter.notifyDataSetChanged();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lend_record_return_iv:
                finish();
                break;
        }
    }

    private void setLVClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            }
        });
    }
}