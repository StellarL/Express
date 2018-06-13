package com.example.packageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 我的接单
 */
public class receiveActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        listView=findViewById(R.id.listviewriv);
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.layout_item);
        listView.setAdapter(adapter);
    }
}
