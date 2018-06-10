package com.example.packageapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class num4Activity extends AppCompatActivity {

    private ListView listView;
    private AllOrdersAdapter allOrdersAdapter;
    private ArrayList<Order> arrayList;
    private SQLiteDatabase sqLiteDatabase;

    //当前用户id
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num4);
        listView = findViewById(R.id.allOrders);

        Intent intent = getIntent();
        //获取用户id
        id = intent.getIntExtra("id",1);

        //初始化arrayList
        initData();
        allOrdersAdapter = new AllOrdersAdapter(num4Activity.this,arrayList);
        listView.setAdapter(allOrdersAdapter);
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
        while (c.moveToNext()){
            int _id = c.getInt(c.getColumnIndex("_id"));
//            int orderId = c.getInt(c.getColumnIndex("order_id"));
            String startPlace = c.getString(c.getColumnIndex("start_place"));
            String endPlace = c.getString(c.getColumnIndex("end_place"));
            int payment = c.getInt(c.getColumnIndex("payment"));
            int type = c.getInt(c.getColumnIndex("type"));
            Order order = new Order(_id,startPlace,endPlace,payment,type);
            Log.e("order", "initData: "+order.toString());
            arrayList.add(order);
        }
    }

}
