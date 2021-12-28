package com.jnu.bookmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jnu.bookmanagementsystem.adapter.BookListViewAdapter;
import com.jnu.bookmanagementsystem.bean.BookBean;
import com.jnu.bookmanagementsystem.db.service.BookService;
import com.jnu.bookmanagementsystem.db.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class Check_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private String flag;
    private Spinner spinner;
    private TextView emptyTv;
    private Button searchBt;
    private ListView searchLv;
    private EditText searchEt;
    private ImageView returnIv;
    private List<BookBean> bookList;
    private BookListViewAdapter bookListViewAdapter;

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_book);
        //初始化控件
        //获取视图资源
        initView();
        //初始化列表集合
        bookList = new ArrayList<>();
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


        //设置适配器，加载每一行数据到列表当中
        bookListViewAdapter = new BookListViewAdapter(this, bookList);
        searchLv.setAdapter(bookListViewAdapter);
        searchLv.setEmptyView(emptyTv);

        returnIv.setOnClickListener(this);
        searchBt.setOnClickListener(this);
        setLVClickListener();
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        flag = (String) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private void initView() {
        spinner = (Spinner) findViewById(R.id.search_flag_spinner);
        emptyTv = findViewById(R.id.search_empty_tv);
        searchBt = findViewById(R.id.search_button_111);
        searchLv = findViewById(R.id.search_lv);
        searchEt = findViewById(R.id.search_head_et);
        returnIv = findViewById(R.id.search_return_iv);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_return_iv:
                finish();
                break;
            case R.id.search_button_111:

                String match = searchEt.getText().toString();
                List<BookBean> list = new ArrayList<>();

                list = bookService.findBook(match);

                //清空账单封装对象数据
                bookList.clear();
                //存入搜索结果账单列表数据
                bookList.addAll(list);
                //启动适配器
                bookListViewAdapter.notifyDataSetChanged();

                break;
        }
    }


    /**
     * 设置ListView的点击响应事件
     */
    private void setLVClickListener() {
        searchLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //获取正在被点击的这条信息
                BookBean currentBean = bookList.get(position);
                //创建意图对象
                Intent intent = new Intent(Check_Activity.this, DetailActivity.class);
                //设置传递键值对
                intent.putExtra("currentBeanId", currentBean.getId());
                //激活意图,跳转界面
                startActivity(intent);
            }
        });
    }
}

