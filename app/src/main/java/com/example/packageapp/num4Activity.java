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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        /*myListener=new AllOrdersAdapter.MyClickListener() {
            @Override
            public void myOnClick(int position, View v) {
                order=arrayList.get(position);
                Log.e("ItemClick", "myOnClick: "+position );
            }
        };*/
    }

    /*@Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.sym_def_app_icon).setTitle("确认信息").setMessage(
                "您确定接下此订单吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(num4Activity.this,num6Activity.class);
                intent.putExtra("id",order.get_id());
                startActivity(intent);

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }*/
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
