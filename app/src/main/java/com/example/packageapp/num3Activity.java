package com.example.packageapp;

import android.app.FragmentManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;


public class num3Activity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    int lastSelectedPosition = 0;
    private String TAG = num3Activity.class.getSimpleName();
    private MyFragment mMyFragment;
    private ScanFragment mScanFragment;
    private HomeFragment mHomeFragment;



    private BottomNavigationBar bottomNavigationBar;
    private Button receiveBtn;
    private Button orderBtn;
    private String username;//用户名 即 登录手机号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num3);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        receiveBtn = findViewById(R.id.receiveButton);
        orderBtn = findViewById(R.id.orderButton);

        //下单事件 点击转发到 num11Activity 填写下单信息
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(num3Activity.this,num11Activity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        //接单事件 转发到num4Activity 获取所有订单列表
        receiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(num3Activity.this,num4Activity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });


        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */

                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */

                .setActiveColor("#FF107FFD") //选中颜色
                .setInActiveColor("#e9e6e6") //未选中颜色
                .setBarBackgroundColor("#1ccbae");//导航栏背景色

        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(android.R.drawable.ic_menu_slideshow,"首页"))
                .addItem(new BottomNavigationItem(android.R.drawable.ic_dialog_info, "订单"))
                .addItem(new BottomNavigationItem(android.R.drawable.sym_def_app_icon, "我的"))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise(); //initialise 一定要放在 所有设置的最后一项

        setDefaultFragment();//设置默认导航栏
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = HomeFragment.newInstance("首页");
        transaction.replace(R.id.tb, mHomeFragment);
        transaction.commit();

    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance("首页");
                }
                transaction.replace(R.id.tb, mHomeFragment);
                break;
            case 1:
                if (mScanFragment == null) {
                    mScanFragment = ScanFragment.newInstance("订单");
                }
                transaction.replace(R.id.tb, mScanFragment);
                break;
            case 2:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance("我的");
                }
                transaction.replace(R.id.tb, mMyFragment);
                break;

            default:
                break;
        }

        transaction.commit();// 事务提交


    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
