package com.example.packageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class orderActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        listView=findViewById(R.id.listview);
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.layout_item);
        listView.setAdapter(adapter);
    }
}
