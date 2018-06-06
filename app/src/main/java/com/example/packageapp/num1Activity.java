package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class num1Activity extends AppCompatActivity {

    private Button btnLogin,btnRegist;
    private EditText userName,psd;
    private CheckBox rmpsd,autolgn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num1);

        btnLogin=findViewById(R.id.login);
        btnRegist=findViewById(R.id.regist);
        userName=findViewById(R.id.userName);
        psd=findViewById(R.id.password);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                startActivityForResult(intent,1);
            }
        });
    }
}
