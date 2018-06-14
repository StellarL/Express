package com.example.packageapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class num6Activity extends AppCompatActivity {

    private DBUtil dbUtil;
    private TextView start_place,end_place,payment,type,order_name,order_phone,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num6);

        start_place = findViewById(R.id.textView16);
        end_place = findViewById(R.id.textView17);
        payment = findViewById(R.id.textViewPayment);
        type = findViewById(R.id.textView19);
        order_name = findViewById(R.id.textView20);
        order_phone = findViewById(R.id.textView21);
        info = findViewById(R.id.textViewInfo);

        Intent intent = getIntent();
        //订单id
        int id = intent.getIntExtra("id",-1);
        String username  = intent.getStringExtra("username");
        String name = intent.getStringExtra("receive_name");
        String phone = intent.getStringExtra("receive_phone");
        dbUtil = new DBUtil(num6Activity.this,"express1.db");
        //查看订单是否完成 如果完成就直接跳到 num7Activity
        Order order1 = dbUtil.queryById(id);
        if(order1.getFinish().equals("已完成")){
            Intent intent1 = new Intent(num6Activity.this,num7Activity.class);
            startActivity(intent1);
        }else {
            dbUtil.updateStete(id, username, name, phone);
            Order order = dbUtil.queryById(id);
            Log.e("num6Activity", "onCreate: " + order.toString());
            start_place.setText(order.getStartPlace());
            end_place.setText(order.getEndPlace());
            payment.setText(String.valueOf(order.getPayment()));
            String type1 = "";
            switch (order.getType()) {
                case 1:
                    type1 = "小";
                    break;
                case 2:
                    type1 = "中";
                    break;
                case 3:
                    type1 = "大";
            }
            type.setText(type1);
            order_name.setText(order.getOrderName());
            order_phone.setText(order.getOrderPhone());
            info.setText(order.getInfo());

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int orderid=data.getIntExtra("id",-1);
    }
}
