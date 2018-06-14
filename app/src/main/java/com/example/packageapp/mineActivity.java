package com.example.packageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class mineActivity extends AppCompatActivity {

    private TextView username,relname,idcard,idimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        username = findViewById(R.id.username);
        relname = findViewById(R.id.relname);
        idcard = findViewById(R.id.idcard);
        idimg = findViewById(R.id.idimg);

        //todo  这里username为上一个传过来 假设为123
        String username1 = "123";

        //根据id 即 用户名 username 获取真实信息
        DBUtil dbUtil = new DBUtil(mineActivity.this,"expressUserUp.db");
        User user = dbUtil.queryRelInfo(username1);
        username.setText(user.getUsername());
        relname.setText(user.getRelName());
        idcard.setText(user.getIdCard());
        idimg.setText(user.getIdImg());
    }
}
