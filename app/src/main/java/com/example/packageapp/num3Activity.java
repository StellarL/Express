package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class num3Activity extends AppCompatActivity {

    private BottomNavigationBar bottomNavigationBar;
    private Button receiveBtn;
    private Button orderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num3);
        bottomNavigationBar = findViewById(R.id.navigation);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"首页"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"订单"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"我的")).initialise();
        receiveBtn = findViewById(R.id.receiveButton);
        orderBtn = findViewById(R.id.orderButton);

        //下单事件 点击转发到 num9Activity 填写下单信息
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(num3Activity.this,num11Activity.class);
                //todo 这里id(用户id )假设为3
                int id = 3;
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

}
