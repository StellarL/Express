package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class num1Activity extends AppCompatActivity {

    /*页面登录注册按钮，用户名密码框，记住密码自动登录勾选框*/
    private Button btnLogin,btnRegist;
    private EditText userName,psd;
    private CheckBox rmpsd,autolgn;
    private int RequestCode=1;
int a = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        userName.setText(data.getStringExtra("name"));
        psd.setText(data.getStringExtra("psd"));
    }

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
                Intent intent=new Intent(num1Activity.this,num1eActivity.class);
                startActivityForResult(intent,RequestCode);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(num1Activity.this,num3Activity.class);
                startActivity(intent);
            }
        });
    }
}
