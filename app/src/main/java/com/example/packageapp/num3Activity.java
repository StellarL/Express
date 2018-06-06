package com.example.packageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class num3Activity extends AppCompatActivity {

    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num3);
        bottomNavigationBar = findViewById(R.id.navigation);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"首页"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"订单"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"我的")).initialise();
    }
}
