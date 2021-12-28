package com.jnu.bookmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jnu.bookmanagementsystem.Librarian.Add_Activity;
import com.jnu.bookmanagementsystem.Librarian.Delete_Activity;
import com.jnu.bookmanagementsystem.Librarian.Edit_Activity;
import com.jnu.bookmanagementsystem.Librarian.Librarian_Lend_Record_Activity;
import com.jnu.bookmanagementsystem.Librarian.Library_Check_Activity;

public class Librarian_function extends AppCompatActivity implements View.OnClickListener {
    private Button addButton;//增添书籍按钮
    private Button findButton;//查询书籍按钮
    private Button alterButton;//修改书籍信息按钮
    private Button cancelButton;//删除书籍按钮
    private Button readerButton;//借阅记录按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_function);
        init();
        addButton.setOnClickListener(this);
        alterButton.setOnClickListener(this);
        findButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        readerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_button:
                Intent intent1=new Intent(Librarian_function.this, Add_Activity.class);
                startActivity(intent1);
                break;
            case R.id.find_button:
                Intent intent3=new Intent(Librarian_function.this, Check_Activity.class);
                startActivity(intent3);
                break;
            case R.id.alter_button:
                Intent intent2=new Intent(Librarian_function.this, Library_Check_Activity.class);
                startActivity(intent2);
                break;
            case R.id.cancel_button:
                Intent intent4=new Intent(Librarian_function.this, Library_Check_Activity.class);
                startActivity(intent4);
                break;

            case R.id.reader_button:
                Intent intent5=new Intent(Librarian_function.this, Librarian_Lend_Record_Activity.class);
                startActivity(intent5);
                break;
            default:
                break;
        }

    }
    private void init(){
        addButton=(Button)findViewById(R.id.add_button);
        findButton=(Button)findViewById(R.id.find_button);
        alterButton=(Button)findViewById(R.id.alter_button);
        cancelButton=(Button)findViewById(R.id.cancel_button);
        readerButton=(Button)findViewById(R.id.reader_button);

    }
}
