package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * 我的下单
 */
public class orderActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Order> arrayList;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        listView=findViewById(R.id.listview);

        //todo username从之前传过来的 这里假设为123
        username = "123";

        initData1();
        MyOrderAdapter adapter = new MyOrderAdapter(this,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemClick", "onItemClick: "+position );
                Order order = arrayList.get(position);
                Log.e("onItemClick", "onItemClick: "+ order.toString() );
                Intent intent = new Intent(orderActivity.this,num9Activity.class);
                Log.e("intent", "onItemClick: _id:"+order.get_id() );
                intent.putExtra("id",order.get_id());
                startActivity(intent);
            }
        });
    }

    private void initData1() {
        DBUtil dbUtil = new DBUtil(orderActivity.this,"express1.db");
        arrayList = dbUtil.selectMyOrder(Integer.valueOf(username));
    }
}
