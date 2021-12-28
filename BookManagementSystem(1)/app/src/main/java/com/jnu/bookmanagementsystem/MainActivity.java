package com.jnu.bookmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.jnu.bookmanagementsystem.db.DBHelper;
import com.jnu.bookmanagementsystem.db.service.UserService;
import com.jnu.bookmanagementsystem.db.service.impl.UserServiceImpl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static int UID;
    private EditText username; //用户名输入框
    private EditText password; //密码输入框
    private Button LoginButton; //登录按钮
    private Button RegisterButton; //注册按钮
    private CheckBox passwordCheck; //显示密码复选框
    private UserService userService = new UserServiceImpl();
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
        initialize(); //初始化各种控件
        ischeck(passwordCheck); //设置密码显示或不显示
        LoginButton.setOnClickListener(this);
        RegisterButton.setOnClickListener(this);
    }

    private void initialize() {
        username = (EditText) findViewById(R.id.username_text); //创建用户名输入框对象
        password = (EditText) findViewById(R.id.password_text); //创建密码输入框对象
        RegisterButton = (Button) findViewById(R.id.register_button); //创建注册按钮对象
        LoginButton = (Button) findViewById(R.id.login_button);  //创建登录按钮对象
        passwordCheck = (CheckBox) findViewById(R.id.password_checkbook); //创建显示密码复选框对象
    }

    private void ischeck(CheckBox passwordCheck) {
        passwordCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //显示明文，即设置为可见的密码
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //不显示明文，即设置为不可见的密码
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button://登录的点击事件
                login();
                break;
            case R.id.register_button://注册的点击事件
                //点击注册按钮时转去第二个活动页面
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void login() {
        //获取输入的用户名与密码
        String usernametext = username.getText().toString().trim();
        String passwordtext = password.getText().toString().trim();

        //调用登录功能
        int uid = userService.login(usernametext, passwordtext);
        if (uid == 0) {
            //如果登录不成功
            Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
            //将用户名框与密码框清空，并把光标放回框中
            this.username.setText("");
            this.password.setText("");
            this.username.requestFocus();
            this.password.requestFocus();

        } else {
            UID=uid;
            //登录成功
            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,UserActivity.class);
            startActivity(intent);
        }
    }


    public void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}