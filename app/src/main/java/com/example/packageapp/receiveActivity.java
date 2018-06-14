package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * 我的接单
 */
public class receiveActivity extends AppCompatActivity {

    private ListView listView1;
    private ArrayList<Order> arrayList;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        listView1 =findViewById(R.id.listviewriv);

        //todo 这里的username从前面传过来 这里假设 "1234"
        username = "1234";

        initData();
        MyOrderAdapter adapter = new MyOrderAdapter(this,arrayList);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = arrayList.get(position);
                if(order.getFinish() .equals("已完成") ){
                    Intent intent = new Intent(receiveActivity.this,num7Activity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(receiveActivity.this,num6Activity.class);
                    intent.putExtra("id",order.get_id());
                    intent.putExtra("username",order.getOrderId());
                    startActivity(intent);
                }
            }
        });
    }

    private void initData() {
        DBUtil dbUtil = new DBUtil(this,"express1.db");
        arrayList = dbUtil.selectMyReceive(Integer.valueOf(username));
    }
}
