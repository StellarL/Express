package com.example.packageapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class num4Activity extends AppCompatActivity  {

    private ListView listView;
    private AllOrdersAdapter allOrdersAdapter;
    private ArrayList<Order> arrayList;
    private DBUtil dbUtil;

    //当前用户用户名
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num4);
        listView = findViewById(R.id.allOrders);

        Intent intent = getIntent();
        //获取用户名
        username = intent.getStringExtra("username");
        Log.e("num4Activity", "username : " + username );
        //初始化arrayList
        initData();
        allOrdersAdapter = new AllOrdersAdapter(num4Activity.this,arrayList,username);
        listView.setAdapter(allOrdersAdapter);

    }


    /*
    初始化ArrayList<Order> 获取数据库Order1表中 state=0的数据
     */
    private void initData() {
        Log.e("initDate", "initData: " );
        dbUtil = new DBUtil(num4Activity.this,"express1.db");
        arrayList = dbUtil.queryAllState0();
    }


}
