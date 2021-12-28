package com.jnu.bookmanagementsystem;


import static com.jnu.bookmanagementsystem.MainActivity.UID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.jnu.bookmanagementsystem.db.service.UserService;
import com.jnu.bookmanagementsystem.db.service.impl.UserServiceImpl;

public class Home {
    private UserService userService = new UserServiceImpl();

    public Home() {

    }

    public void initNV(NavigationView navigationview, DrawerLayout drawer, Activity activity) {
        View headerView = navigationview.getHeaderView(0);

        //寻找头部里面的控件
        ImageView imageView = headerView.findViewById(R.id.iv_head);
        Context context = drawer.getContext();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity.getApplicationContext(), "点击了头像", Toast.LENGTH_LONG).show();
            }
        });
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
        ColorStateList csl = (ColorStateList) activity.getResources().getColorStateList(R.color.nav_menu_text_color);
        //设置item的条目颜色
        navigationview.setItemTextColor(csl);
        //去掉默认颜色显示原来颜色  设置为null显示本来图片的颜色
        navigationview.setItemIconTintList(csl);

        //设置条目点击监听
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                //点击哪个按钮
                switch (menuItem.getItemId()) {
                    case R.id.single_1:
                        //首页
//                        Toast.makeText(activity.getApplicationContext(), "首页", Toast.LENGTH_LONG).show();
                        drawer.closeDrawers();
                        intent = new Intent(context, UserActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.single_2:
                        //个人主页
                        Toast.makeText(activity.getApplicationContext(), "我的借书", Toast.LENGTH_LONG).show();
                        intent = new Intent(context, MyBorrowRecordActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.single_3:
                        //管理员入口
                        int kind = userService.findKindById(UID);
                        if (kind == 1) {
                            Toast.makeText(activity.getApplicationContext(), "进入管理模式", Toast.LENGTH_LONG).show();
                            intent = new Intent(context, Librarian_function.class);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(activity.getApplicationContext(), "权限不足", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.single_4:
                        //查找书籍
                        Toast.makeText(activity.getApplicationContext(), "查找书籍", Toast.LENGTH_LONG).show();
                        intent = new Intent(context, Check_Activity.class);
                        context.startActivity(intent);
                }
//                Toast.makeText(activity.getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_LONG).show();
                //设置哪个按钮被选中
//                menuItem.setChecked(true);
                //关闭侧边栏
//                drawer.closeDrawers();
                return false;
            }
        });
    }
}

