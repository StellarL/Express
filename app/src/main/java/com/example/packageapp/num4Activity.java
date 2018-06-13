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
    private SQLiteDatabase sqLiteDatabase;
    private  Order order;

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

        //初始化arrayList
        initData();
        allOrdersAdapter = new AllOrdersAdapter(num4Activity.this,arrayList);
        listView.setAdapter(allOrdersAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(num4Activity.this);
                builder.setTitle("确认订单");
                builder.setMessage("您确定要接下此订单吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(num4Activity.this,num6Activity.class));
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });


    }


    /*
    初始化ArrayList<Order> 获取数据库Order1表中 state=0的数据
     */
    private void initData() {
        Log.e("initDate", "initData: " );
        sqLiteDatabase = this.openOrCreateDatabase("express.db",MODE_PRIVATE,null);
        String strSelect = "select * from order1 where state = 0";
        Cursor c = sqLiteDatabase.rawQuery(strSelect,null);
        arrayList = new ArrayList<Order>();
        while (c.moveToNext()) {
            //订单id
            int _id = c.getInt(c.getColumnIndex("_id"));
            //下单人id 即用户名 即电话号码
//            int orderId = c.getInt(c.getColumnIndex("order_id"));
            String startPlace = c.getString(c.getColumnIndex("start_place"));
            String endPlace = c.getString(c.getColumnIndex("end_place"));
            int payment = c.getInt(c.getColumnIndex("payment"));
            int type = c.getInt(c.getColumnIndex("type"));
            Order order = new Order(_id, startPlace, endPlace, payment, type);
//            Log.e("order", "initData: " + order.toString());
            arrayList.add(order);
        }
    }


}
