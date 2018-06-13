package com.example.packageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * 我的下单
 */
public class orderActivity extends AppCompatActivity {

    private ListView listView;
    private DBUtil dbUtil;
    private ArrayList<Order> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        listView=findViewById(R.id.listview);
//        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.layout_item);
        MyOrderAdapter adapter = new MyOrderAdapter(this,arrayList);
        listView.setAdapter(adapter);



    }
}
