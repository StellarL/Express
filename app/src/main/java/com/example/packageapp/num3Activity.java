package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class num3Activity extends AppCompatActivity {

    //选项卡
    private TabHost tabHost;
    private TabWidget tabWidget;

    private BottomNavigationBar bottomNavigationBar;
    private Button receiveBtn;
    private Button orderBtn;
    private String username;//用户名 即 登录手机号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num3);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        receiveBtn = findViewById(R.id.receiveButton);
        orderBtn = findViewById(R.id.orderButton);

        //下单事件 点击转发到 num11Activity 填写下单信息
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(num3Activity.this,num11Activity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        //接单事件 转发到num4Activity 获取所有订单列表
        receiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(num3Activity.this,num4Activity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        //选项卡
        tabHost=findViewById(R.id.tabhost);
        tabWidget=findViewById(android.R.id.tabs);
        tabWidget.setDividerDrawable(null);//设置无竖线
        String[] titles={"首页","订单","我的"};
        String[] tags={"tag1","tag2","tag3"};
        Intent[] intents={null,new Intent(this,num8Activity.class),new Intent(this,mineActivity.class)};
        for(int i =0 ;i <3;i++){
            LayoutInflater inflater=this.getLayoutInflater();
            View view=inflater.inflate(R.layout.layout_tab1,null);
            TextView textView=view.findViewById(R.id.textView39);
            textView.setText(titles[i]);
            //创建选项卡
            TabHost.TabSpec spec=tabHost.newTabSpec(tags[i]);
            spec.setIndicator(view);
            //设置每页内容
            spec.setContent(intents[i]);
            //将选项卡添加至tabHost上
            tabHost.addTab(spec);
        }
    }

}
