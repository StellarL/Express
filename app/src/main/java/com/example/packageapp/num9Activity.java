package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class num9Activity extends AppCompatActivity {

    private TextView receive_name,receive_phone,info;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num9);

        receive_name = findViewById(R.id.textpersonName);
        receive_phone = findViewById(R.id.phoneNum);
        info = findViewById(R.id.textframe);
        button = findViewById(R.id.button);

        Intent intent = getIntent();
        //订单id
        final String id = intent.getIntExtra("id",0)+"";
        Log.e("num9", "onCreate: id : "+ id);
        final DBUtil dbUtil = new DBUtil(num9Activity.this,"express1.db");
        Order order = dbUtil.queryById(Integer.valueOf(id));
        Log.e("order", "onCreate: " +order.toString() );
        receive_name.setText(order.getReceiveName());
        receive_phone.setText(order.getReceivePhone());
        info.setText(order.getInfo());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新订单已完成
                DBUtil dbUtil1 = new DBUtil(num9Activity.this,"express1.db");
                dbUtil.updateFinish(Integer.valueOf(id));
                Intent intent1 = new Intent(num9Activity.this,orderActivity.class);
                startActivity(intent1);
            }
        });
    }
}
