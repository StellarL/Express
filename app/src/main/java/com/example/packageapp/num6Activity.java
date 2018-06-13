package com.example.packageapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class num6Activity extends AppCompatActivity {

    private DBUtil dbUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num6);

        Intent intent = getIntent();
        //点单id
        int id = intent.getIntExtra("id",-1);
        String username  = intent.getStringExtra("username");
        dbUtil = new DBUtil(num6Activity.this,"express.db");
        dbUtil.updateStete(id,username);
        Order order = dbUtil.queryById(id);
        Log.e("num6Activity", "onCreate: " + order.toString() );
        //todo 将order中的内容写到界面

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int orderid=data.getIntExtra("id",-1);
    }
}
