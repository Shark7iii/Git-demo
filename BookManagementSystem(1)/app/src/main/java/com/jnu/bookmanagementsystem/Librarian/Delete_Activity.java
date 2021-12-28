package com.jnu.bookmanagementsystem.Librarian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jnu.bookmanagementsystem.R;

public class Delete_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEt,kindEt;
    private Button ensureBtn,cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_delete_book);
        //初始化控件
        initialize();
    }

    private void initialize() {
        nameEt=findViewById(R.id.delete_book_name_edit);
        kindEt=findViewById(R.id.delete_book_kind_edit);
        ensureBtn=findViewById(R.id.delete_yes_button);
        cancelBtn=findViewById(R.id.delete_no_button);
        ensureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}