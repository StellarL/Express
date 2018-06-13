package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class num8Activity extends AppCompatActivity {

    private TabHost tabHost;
    private TabWidget tabWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num8);

        //接单下单选项卡
        tabHost=findViewById(R.id.tabhost);
        tabHost.setup();
        tabWidget=findViewById(android.R.id.tabs);
        tabWidget.setDividerDrawable(null);//设置无竖线
        String[] titles={"我的下单","我的接单"};
        String[] tags={"tag1","tag2"};
        Intent[] intents={new Intent(this,orderActivity.class),new Intent(this,receiveActivity.class)};
        for(int i =0 ;i <2;i++){
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
