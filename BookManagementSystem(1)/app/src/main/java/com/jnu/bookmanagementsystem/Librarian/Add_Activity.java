package com.jnu.bookmanagementsystem.Librarian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jnu.bookmanagementsystem.R;
import com.jnu.bookmanagementsystem.bean.BookBean;
import com.jnu.bookmanagementsystem.db.service.BookService;
import com.jnu.bookmanagementsystem.db.service.LendService;
import com.jnu.bookmanagementsystem.db.service.impl.BookServiceImpl;
import com.jnu.bookmanagementsystem.db.service.impl.LendServiceImpl;

public class Add_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEt, kindEt, writerEt, moneyEt;
    private Button ensureBtn, cancelBtn;
    private BookBean book = new BookBean();
    private BookService bookService = new BookServiceImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_add_book);
        //初始化控件
        initialize();
    }

    private void initialize() {
        nameEt = findViewById(R.id.add_book_name_edit);
        kindEt = findViewById(R.id.add_book_kind_edit);
        writerEt = findViewById(R.id.add_book_writer_edit);
        moneyEt = findViewById(R.id.add_book_money_edit);
        ensureBtn = findViewById(R.id.add_yes_button);
        cancelBtn = findViewById(R.id.add_no_button);
        ensureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_yes_button:
                String nameStr = nameEt.getText().toString();
                String kindStr = kindEt.getText().toString();
                String writerStr = writerEt.getText().toString();
                //获取用户输入的金额
                String moneyStr = moneyEt.getText().toString();
                if (TextUtils.isEmpty(moneyStr) || moneyStr.equals("0")) {
                    finish();
                    return;
                }
                //字符串转基本类型
                float M = Float.parseFloat(moneyStr);

                book.setBookName(nameStr);
                book.setBookKind(kindStr);
                book.setWriter(writerStr);
                book.setMoney(M);
                book.setFlag(1);
                book.setImageId(R.drawable.book_1);
                book.setYear(1990);
                book.setMonth(1);
                book.setDay(1);
                book.setTime("1990-01-01");
                book.setUserId(0);
                bookService.addBook(book);


                Toast.makeText(this, "添加新书籍成功", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.cancel_button:
                break;
        }
    }
}