package com.example.packageapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

public class num8Activity extends Activity implements BottomNavigationBar.OnTabSelectedListener ,TabHost.OnTabChangeListener{

    int lastSelectedPosition = 1;
    private String TAG = num3Activity.class.getSimpleName();
    private MyFragment mMyFragment;
    private ScanFragment mScanFragment;
    private HomeFragment mHomeFragment;
    private BottomNavigationBar bottomNavigationBar;
    private ListView listView;
    private ArrayList<Order> arrayList;

    private TabHost tabHost;
    private String tab1="我的下单",tab_2="我的接单";
    private Intent intent1;
    private Intent intent2;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num8);

        listView = findViewById(R.id.orderListview);

        username = getIntent().getStringExtra("username");

        intent1=new Intent(this,orderActivity.class);
        intent2=new Intent(this,receiveActivity.class);
        //接单下单选项卡
        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec(tab1)
                .setIndicator("我的下单")
                .setContent(R.id.tab1));

        tabHost.addTab(tabHost.newTabSpec(tab_2)
                .setIndicator("我的接单")
                .setContent(R.id.tab2));
        tabHost.setCurrentTab(0);   //防止重复点击 产生错误
        tabHost.setOnTabChangedListener(this);

        //底部导航栏
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
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
        mScanFragment = ScanFragment.newInstance("订单");
        transaction.replace(R.id.tb, mScanFragment);
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
                    Intent intent = new Intent(num8Activity.this,num3Activity.class);
                    startActivity(intent);
                    finish();
//                    mHomeFragment = HomeFragment.newInstance("首页");
                }
//                transaction.replace(R.id.tb, mHomeFragment);
                break;
            case 1:
                if (mScanFragment == null) {
                    mScanFragment = ScanFragment.newInstance("订单");
//                    Intent intent = new Intent(num8Activity.this,num8Activity.class);
//                    startActivity(intent);
//                    finish();
                }
                transaction.replace(R.id.tb, mScanFragment);
                break;
            case 2:
                if (mMyFragment == null) {
//                    mMyFragment = MyFragment.newInstance("我的");
                    Intent intent = new Intent(num8Activity.this,mineActivity.class);
                    startActivity(intent);
                    finish();
                }
//                transaction.replace(R.id.tb, mMyFragment);
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

    @Override
    public void onTabChanged(String s) {
        if (s.equals(tab1)) {
//            startActivity(intent1);
            initData1();
            MyOrderAdapter adapter = new MyOrderAdapter(this,arrayList);
            listView.setAdapter(adapter);
        } else {
//            startActivity(intent2);
            initData();
            MyOrderAdapter adapter = new MyOrderAdapter(this,arrayList);
            listView.setAdapter(adapter);
        }
    }
    private void initData1() {
        DBUtil dbUtil = new DBUtil(num8Activity.this,"express1.db");
        arrayList = dbUtil.selectMyOrder(Integer.valueOf(username));
    }

    private void initData() {
        DBUtil dbUtil = new DBUtil(this,"express1.db");
        arrayList = dbUtil.selectMyReceive(Integer.valueOf(username));
    }
}
