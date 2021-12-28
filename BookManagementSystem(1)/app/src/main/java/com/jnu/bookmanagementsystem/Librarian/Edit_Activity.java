package com.jnu.bookmanagementsystem.Librarian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jnu.bookmanagementsystem.R;
import com.jnu.bookmanagementsystem.bean.BookBean;
import com.jnu.bookmanagementsystem.db.service.BookService;
import com.jnu.bookmanagementsystem.db.service.impl.BookServiceImpl;


public class Edit_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText newMoneyEt, newNameEt, newKindEt, newWriterEt;
    private Button ensureBtn, cancelBtn;
    private BookService bookService = new BookServiceImpl();
    private BookBean book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_edit_book);

        //获取意图对象
        Intent intent = getIntent();
        //获取传递的账单ID值
        int currentBeanId = intent.getIntExtra("currentBeanId", 0);
        book = bookService.getBookById(currentBeanId);

        //初始化控件
        initialize();

        newWriterEt.setText(book.getWriter());
        newMoneyEt.setText(String.format("%.2f", book.getMoney()));
        newNameEt.setText(book.getBookName());
        newKindEt.setText(book.getBookKind());

    }

    private void initialize() {
        newMoneyEt = findViewById(R.id.edit_new_book_money_edit);
        newNameEt = findViewById(R.id.edit_new_book_name_edit);
        newKindEt = findViewById(R.id.edit_new_book_kind_edit);
        newWriterEt = findViewById(R.id.edit_new_book_writer_edit);
        ensureBtn = findViewById(R.id.edit_yes_button);
        cancelBtn = findViewById(R.id.edit_no_button);
        ensureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_no_button:
                finish();
                break;
            case R.id.edit_yes_button:

                book.setBookName(newNameEt.getText().toString());
                book.setBookKind(newKindEt.getText().toString());
                book.setWriter(newWriterEt.getText().toString());
                book.setMoney(Float.parseFloat(newMoneyEt.getText().toString()));

                bookService.freshBook(book);

                finish();
                break;
        }
    }
}