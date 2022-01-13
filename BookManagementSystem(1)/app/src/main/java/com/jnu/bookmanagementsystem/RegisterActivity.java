package com.jnu.bookmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jnu.bookmanagementsystem.db.service.UserService;
import com.jnu.bookmanagementsystem.db.service.impl.UserServiceImpl;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText new_username;//注册页面的用户名输入框
    private EditText new_password;//注册页面的密码输入框
    private EditText new_again_password;//注册页面的再次输入密码框
    private Button new_register;//注册按钮
    private Button back;//返回按钮
    private CheckBox ispassword;//显示密码按钮
    private UserService userService = new UserServiceImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();//初始化控件
        ischeck(ispassword);
        new_register.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void init() {
        new_username=(EditText)findViewById(R.id.newusername_text);// Create a user name input box object for the registration page
        new_password=(EditText)findViewById(R.id.newpassword_text);// Create a password input field object for the registration page
        new_again_password=(EditText)findViewById(R.id.new_again_password_text);// Create a re-confirm password input box object for the registration page
        new_register=(Button)findViewById(R.id.yes_register_button);// Create a registration button object
        back=(Button)findViewById(R.id.back_button);// Create a return button object
        ispassword=(CheckBox)findViewById(R.id.new_password_checkbook);// Create a display password button object
    }
    private void register(){
        // Get the entered user name and password
        String usernametext=new_username.getText().toString().trim();
        String passwordtext=new_password.getText().toString().trim();
        int flag=0;
        // Verify that the entered passwords are the same
        if (usernametext.equals("") || passwordtext.equals("")){
            Toast.makeText(RegisterActivity.this,"帐号或密码不能为空",Toast.LENGTH_LONG).show();
            flag++;
        }

        String again_password=new_again_password.getText().toString().trim();
        if(!again_password.equals(passwordtext)){
            Toast.makeText(RegisterActivity.this,"密码不一致",Toast.LENGTH_LONG).show();
            flag++;
        }
        if (flag == 0) {
            // Call the registration function
            boolean result = userService.register(usernametext, passwordtext, 0);//判断是否注册成功
            if (result) {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_LONG).show();
                // Clear the username field text and place the input cursor back in the input field
                this.new_username.setText("");
                this.new_username.requestFocus();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yes_register_button:
                register();//校验输入的用户名与密码
                break;
            case R.id.back_button:
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    /**
     * 设置显示密码的点击操作
     *
     */
    private void ischeck(CheckBox passwordCheck){
        passwordCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // Display plain text, that is, set to visible password
                    new_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    new_again_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    // Do not display the plaintext, that is, set the password to invisible
                    new_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    new_again_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }
}